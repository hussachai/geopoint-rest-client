/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.siberhus.geopoint.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siberhus.geopoint.restclient.response.IpInfo;
import com.siberhus.geopoint.restclient.response.IpInfoWrapper;

/**
 * 
 * @author Hussachai Puripunpinyo (http://www.siberhus.com)
 * 
 */
public class GeoPointRestClient {
	
	private Logger log = LoggerFactory.getLogger(GeoPointRestClient.class);
	
	private String serviceUrl = "http://api.quova.com/v1/ipinfo/";
	
	private String apiKey;
	
	private String secret;
	
	/**
	 * 
	 * @param ipAddr
	 * @param format
	 * @return
	 */
	public IpInfo query(String ipAddr, Format format) {
		
		Assert.notNull("apiKey", apiKey);
		Assert.notNull("secret", secret);
		
		Assert.notNull("ipAddr", ipAddr);
		Assert.notNull("format", format);
		
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// should not happen
			throw new RuntimeException(e);
		}
		
		String formatStr = format == Format.XML ? "xml" : "json";
		
		long timeInSeconds = (long) (System.currentTimeMillis() / 1000);
		
		String input = apiKey + secret + timeInSeconds;
		digest.update(input.getBytes());
		String signature = String.format("%032x", new BigInteger(1, digest.digest()));
		
		String url = serviceUrl + ipAddr + "?apikey=" + apiKey + "&sig=" + signature
				+ "&format=" + formatStr;
		log.debug("Calling Quova ip2loc service from URL: {}", url);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		// Create an HTTP GET request
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		httpget.getRequestLine();
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (IOException e) {
			throw new HttpRequestException(e);
		}

		HttpEntity entity = response.getEntity();
		StatusLine status = response.getStatusLine();
		if(status.getStatusCode()!=200){
			throw new HttpRequestException(status.getReasonPhrase());
		}
		// Print the response
		log.debug("Response status: {}", status);
		
		StringBuilder responseText = new StringBuilder();
		if (entity != null) {
			try {
				InputStream inputStream = entity.getContent();
				// Process the response
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					responseText.append(line);
				}
				bufferedReader.close();
			} catch (IOException e) {
				throw new HttpRequestException(e);
			}
		}
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources.
		httpclient.getConnectionManager().shutdown();
		
		IpInfo ipInfo = null;
		if (format == Format.XML) {
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(IpInfo.class);
				Unmarshaller um = context.createUnmarshaller();
				ipInfo = (IpInfo) um.unmarshal(new StringReader(responseText
						.toString()));
			} catch (JAXBException e) {
				throw new ResultParseException(e);
			}
		} else {
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				ipInfo = mapper.readValue(
						new StringReader(responseText.toString()),
						IpInfoWrapper.class).getIpInfo();
			} catch (IOException e) {
				throw new ResultParseException(e);
			}
		}
		return ipInfo;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}
	
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
