geopoint-rest-client
====================

Java REST Client for Neustar's IP Intelligence

See the official REST API guide
http://developer.quova.com/docs

You can use the code snippet that is provided in the guide but 
the result you get is raw data whether in XML or JSON format. 
The result data format depends on your configuration. 
It quite takes time to extract the data from the XML or JSON format. 
That's why this library comes into play.

Before testing, you need to obtain the API key and secret in order to
use the free GeoPoint service from Neustar IP Intelligence

Register to use the service here: http://developer.quova.com/member/register

Example
---------

```java
GeoPointRestClient client = new GeoPointRestClient();
client.setApiKey(getSetting("api.key"));
client.setSecret(getSetting("api.secret"));
//Available formats are JSON and XML
IpInfo ipInfo = client.query(getSetting("test.ip"), Format.JSON);
```


I'm not an employee of Neustar. Use this library at your own risk.
This software is under Apache2 license.
