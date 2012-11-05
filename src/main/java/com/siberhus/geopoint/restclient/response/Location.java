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
package com.siberhus.geopoint.restclient.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Hussachai Puripunpinyo (http://www.siberhus.com)
 * 
 */
@JsonIgnoreProperties({"geonames_id", "region_ref_id"})
@XmlRootElement
@XmlType(propOrder = { "continent", "latitude", "longitude", "countryData",
		"region", "stateData", "dma", "msa", "cityData" })
public class Location {

	private String continent;
	
	private Double latitude;
	
	private Double longitude;
	
	@JsonProperty("CountryData")
	private CountryData countryData;
	
	private String region;
	
	@JsonProperty("StateData")
	private StateData stateData;
	
	private String dma;
	
	private String msa;
	
	@JsonProperty("CityData")
	private CityData cityData;
	
	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@XmlElement(name = "CountryData")
	public CountryData getCountryData() {
		return countryData;
	}

	public void setCountryData(CountryData countryData) {
		this.countryData = countryData;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@XmlElement(name = "StateData")
	public StateData getStateData() {
		return stateData;
	}

	public void setStateData(StateData stateData) {
		this.stateData = stateData;
	}

	public String getDma() {
		return dma;
	}

	public void setDma(String dma) {
		this.dma = dma;
	}

	public String getMsa() {
		return msa;
	}

	public void setMsa(String msa) {
		this.msa = msa;
	}

	@XmlElement(name = "CityData")
	public CityData getCityData() {
		return cityData;
	}

	public void setCityData(CityData cityData) {
		this.cityData = cityData;
	}
}