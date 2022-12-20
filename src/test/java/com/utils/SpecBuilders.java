package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {
	public static Properties loadPropertyFile() throws IOException {
		 FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream("project.properties");
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	}
	
	public static JsonPath getJsonpath(Response response) {
		JsonPath js = new JsonPath(response.asString());
		
		return js;
		
	}
	public static RequestSpecification RequestBuildSpec() throws IOException {
		Properties prop = loadPropertyFile();
		RequestSpecification request = null;
		PrintStream print = new PrintStream(new FileOutputStream("log.txt"));
		request = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURI")).setContentType(ContentType.JSON).setRelaxedHTTPSValidation().
				addFilter(RequestLoggingFilter.logRequestTo(print)).addFilter(ResponseLoggingFilter.logResponseTo(print)).build();
		return request;
		
	}
	
	public static ResponseSpecification ResponseBuildSpec() {
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.JSON).setDefaultParser(Parser.JSON).build();
		return response;
	}
	
	
	
}
