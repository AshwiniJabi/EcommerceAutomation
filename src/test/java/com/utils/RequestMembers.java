package com.utils;

import java.util.Map;

public class RequestMembers {
	String baseUrl;
	String resource;
	String httpCall;
	Map<String,String> queryParams;
	Map<String,String> pathParams;
	Map<String,String> headerValues;
	String contentType;
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String gethttpCall() {
		return httpCall;
	}
	public void sethttpCall(String httpCall) {
		this.httpCall = httpCall;
	}
	public Map<String, String> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}
	public Map<String, String> getPathParams() {
		return pathParams;
	}
	public void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	public Map<String, String> getHeaderValues() {
		return headerValues;
	}
	public void setHeaderValues(Map<String, String> headerValues) {
		this.headerValues = headerValues;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	

}
