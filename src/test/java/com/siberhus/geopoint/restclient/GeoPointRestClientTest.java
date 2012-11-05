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

import junit.framework.Assert;

import org.junit.Test;

import com.siberhus.geopoint.restclient.response.CityData;
import com.siberhus.geopoint.restclient.response.CountryData;
import com.siberhus.geopoint.restclient.response.Domain;
import com.siberhus.geopoint.restclient.response.IpInfo;
import com.siberhus.geopoint.restclient.response.Location;
import com.siberhus.geopoint.restclient.response.Network;
import com.siberhus.geopoint.restclient.response.StateData;

/**
 * 
 * @author Hussachai Puripunpinyo (http://www.siberhus.com)
 * 
 */
public class GeoPointRestClientTest extends BaseTestCase{
	
	@Test(expected=HttpRequestException.class)
	public void testWrongCredential(){
		GeoPointRestClient client = new GeoPointRestClient();
		client.setApiKey("100.asdsadafefewfewfwefew");
		client.setSecret("wrongsecret");
		client.query(getSetting("test.ip"), Format.JSON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMissingParameters(){
		GeoPointRestClient client = createClient();
		client.query(null, null);
	}
	
	@Test
	public void testJsonResponse(){
		
		GeoPointRestClient client = createClient();
		IpInfo ipInfo = client.query(getSetting("test.ip"), Format.JSON);
		testResult(ipInfo);
	}
	
	@Test
	public void testXmlResponse(){
		
		GeoPointRestClient client = createClient();
		IpInfo ipInfo = client.query(getSetting("test.ip"), Format.XML);
		testResult(ipInfo);
	}
	
	protected void testResult(IpInfo ipInfo){
		
		Assert.assertNotNull(ipInfo);
		Assert.assertEquals(getSetting("test.ip"), ipInfo.getIpAddress());
		Assert.assertNotNull(ipInfo.getIpType());
		
		Location location = ipInfo.getLocation();
		Assert.assertNotNull(location);
		
		CityData city = location.getCityData();
		Assert.assertNotNull(city);
		
		Assert.assertEquals(getSetting("test.location.city"), city.getCity());
		Assert.assertEquals(getSetting("test.location.city.areaCode"), city.getAreaCode());
		Assert.assertEquals(getSetting("test.location.city.cf"), city.getCityCf());
		Assert.assertEquals(getSetting("test.location.city.timezone"), city.getTimezone());
		Assert.assertEquals(getSetting("test.location.city.postalCode"), city.getPostalCode());
		
		Assert.assertEquals(getSetting("test.location.continent"), location.getContinent());
		
		CountryData country = location.getCountryData();
		Assert.assertNotNull(country);
		
		Assert.assertEquals(getSetting("test.location.country"), country.getCountry());
		Assert.assertEquals(getSetting("test.location.country.code"), country.getCountryCode());
		
		Assert.assertEquals(getSetting("test.location.dma"), location.getDma());
		Assert.assertEquals(getSetting("test.location.latitude", Double.class), location.getLatitude());
		Assert.assertEquals(getSetting("test.location.longitude", Double.class), location.getLongitude());
		Assert.assertEquals(getSetting("test.location.msa"), location.getMsa());
		Assert.assertEquals(getSetting("test.location.region"), location.getRegion());
		
		StateData state = location.getStateData();
		Assert.assertNotNull(state);
		Assert.assertEquals(getSetting("test.location.state"), state.getState());
		Assert.assertEquals(getSetting("test.location.state.cf"), state.getStateCf());
		Assert.assertEquals(getSetting("test.location.state.code"), state.getStateCode());
		
		Network network = ipInfo.getNetwork();
		Assert.assertNotNull(network);
		
		Assert.assertEquals(getSetting("test.network.asn"), network.getAsn());
		Assert.assertEquals(getSetting("test.network.carrier"), network.getCarrier());
		Assert.assertEquals(getSetting("test.network.connectionType"), network.getConnectionType());
		Assert.assertEquals(getSetting("test.network.carrier"), network.getCarrier());
		
		Domain domain = network.getDomain();
		Assert.assertNotNull(domain);
		
		Assert.assertEquals(getSetting("test.network.domain.sld"), domain.getSld());
		Assert.assertEquals(getSetting("test.network.domain.tld"), domain.getTld());
		
		Assert.assertEquals(getSetting("test.network.ipRoutingType"), network.getIpRoutingType());
		Assert.assertEquals(getSetting("test.network.lineSpeed"), network.getLineSpeed());
		Assert.assertEquals(getSetting("test.network.organization"), network.getOrganization());
	
	}
	
	
}
