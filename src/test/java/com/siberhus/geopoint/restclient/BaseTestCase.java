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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Hussachai Puripunpinyo (http://www.siberhus.com)
 * 
 */
public class BaseTestCase {
	
	private static Properties props = new Properties();
	
	@BeforeClass
	public static void loadSettings() throws IOException{
		
		InputStream in = BaseTestCase.class.getResourceAsStream("/settings.properties");
		if(in==null){
			in = BaseTestCase.class.getResourceAsStream("/com/siberhus/geopoint/restclient/settings.properties");
		}
		try{
			props.load(in);
		}finally{
			if(in!=null) in.close();
		}
	}
	
	public static String getSetting(String key){
		return props.getProperty(key);
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T getSetting(String key, Class<T> type){
		String valueStr = getSetting(key);
		Object value = null;
		if(Integer.class.isAssignableFrom(type)){
			value = new Integer(valueStr);
		}else if(Long.class.isAssignableFrom(type)){
			value = new Long(valueStr);
		}else if(Double.class.isAssignableFrom(type)){
			value = new Double(valueStr);
		}else if(Float.class.isAssignableFrom(type)){
			value = new Float(valueStr);
		}
		
		return (T)value;
	}
	
	public GeoPointRestClient createClient(){
		
		GeoPointRestClient client = new GeoPointRestClient();
		client.setServiceUrl(getSetting("api.url"));
		client.setApiKey(getSetting("api.key"));
		client.setSecret(getSetting("api.secret"));
		return client;
	}
	
	@Test
	public void loadSetting(){
		
		Assert.assertNotNull(getSetting("test.ip"));
		Assert.assertNotNull(getSetting("api.url"));
		Assert.assertNotNull(getSetting("api.key"));
		Assert.assertNotNull(getSetting("api.secret"));
		
	}
}
