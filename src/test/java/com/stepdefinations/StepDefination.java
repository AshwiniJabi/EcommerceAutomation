package com.stepdefinations;

import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import com.utils.SpecBuilders;

public class StepDefination extends SpecBuilders {
	Properties prop = null;
	RequestSpecification request= null;
	ResponseSpecification resSpec = null;
	Response response = null;
	JsonPath responseJson = null;
	@Given("User has payload")
	public void user_has_payload() throws IOException {
		
	    // Write code here that turns the phrase above into concrete actions
		prop = loadPropertyFile();
		RestAssured.baseURI =prop.getProperty("baseURI");
		RequestSpecification reqSpec = RequestBuildSpec();
		request = given().spec(reqSpec);
		
	}
	

	@When("User calls {string} http request")
	public void user_calls_http_request(String string) {
		ResponseSpecification resSpec = ResponseBuildSpec();
	  response = request.when().get("status").then().spec(resSpec).extract().response();
	  System.out.println(response.asString());
	}




	@Then("verify the {string} is {int}")
	public void verify_the_is(String string, Integer code) {
	    // Write code here that turns the phrase above into concrete actions
		int statusCode = response.statusCode();
		assertEquals(statusCode, code);
	  
	}
	@Then("response body {string} is {string}")
	public void response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		responseJson = getJsonpath(response);
		String exepectedStatus = responseJson.get(key).toString();
		System.out.println(exepectedStatus);
		assertEquals(value, exepectedStatus);
	    
	}



}
