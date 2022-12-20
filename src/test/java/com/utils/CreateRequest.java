package com.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class CreateRequest extends SpecBuilders  {
	Properties prop= null;
	RequestSpecBuilder request = null;
	public void createFullRequest(RequestMembers reqObj) throws IOException {
		prop = loadPropertyFile();
		RequestMembers requestObj = new RequestMembers();
		requestObj.setBaseUrl(prop.getProperty("baseURI"));
		request =new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURI"));
		String contentType = requestObj.getContentType();
		addContentToRequest(contentType);
		Map<String,String> query = requestObj.getQueryParams();
		addQueryParams(query);
		addPathParam(requestObj.getPathParams());
		addHeaders(requestObj.getHeaderValues());
	}
	private void addHeaders(Map<String, String> headerValues) {
		if (headerValues.isEmpty()) {
			System.out.println("NO header values");
			
		}
		else {
			request = request.addHeaders(headerValues);
		}
		
	}
	private void addPathParam(Map<String, String> pathParams) {
		if (pathParams.isEmpty()) {
			System.out.println("No path param");
			
		}
		else {
			request=request.addPathParams(pathParams);
		}
		
	}
	private void addQueryParams(Map<String, String> query) {
		if(query.isEmpty()) {
			System.out.println("No query params");
		}
		else {
			request =request.addQueryParams(query);
		}
		
		
	}
	private void addContentToRequest(String contentType) {
	if(contentType.equalsIgnoreCase("JSON")) {
		request=request.setContentType(ContentType.JSON);
	}
	
	else if(contentType.equalsIgnoreCase("XML")) {
		request=request.setContentType(ContentType.XML);
	}
		
	}
}
