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

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Hussachai Puripunpinyo (http://www.siberhus.com)
 * 
 */
@XmlRootElement
@XmlType(propOrder = { "organization", "carrier", "asn", "connectionType",
		"lineSpeed", "ipRoutingType", "domain" })
public class Network {

	private String organization;
	
	private String carrier;
	
	private String asn;
	
	@JsonProperty("connection_type")
	private String connectionType;
	
	@JsonProperty("line_speed")
	private String lineSpeed;
	
	@JsonProperty("ip_routing_type")
	private String ipRoutingType;
	
	@JsonProperty("Domain")
	private Domain domain;
	
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	@XmlElement(name = "connection_type")
	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	@XmlElement(name = "line_speed")
	public String getLineSpeed() {
		return lineSpeed;
	}

	public void setLineSpeed(String lineSpeed) {
		this.lineSpeed = lineSpeed;
	}

	@XmlElement(name = "ip_routing_type")
	public String getIpRoutingType() {
		return ipRoutingType;
	}

	public void setIpRoutingType(String ipRoutingType) {
		this.ipRoutingType = ipRoutingType;
	}

	@XmlElement(name = "Domain")
	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
}