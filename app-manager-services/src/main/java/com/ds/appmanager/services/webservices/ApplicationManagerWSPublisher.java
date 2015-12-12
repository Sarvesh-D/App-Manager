package com.ds.appmanager.services.webservices;

import javax.xml.ws.Endpoint;

public class ApplicationManagerWSPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:1122/ams", new ApplicationManagementWSImpl());
		
	}

}
