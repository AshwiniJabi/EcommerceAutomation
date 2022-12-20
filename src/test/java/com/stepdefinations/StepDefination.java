package com.stepdefinations;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import com.utils.CreateRequest;
import com.utils.RequestMembers;
import com.utils.SpecBuilders;

public class StepDefination extends SpecBuilders {
	Properties prop = null;
	RequestSpecification request= null;
	ResponseSpecification resSpec = null;
	Response response = null;
	JsonPath responseJson = null;
	Map<String,String> queryValues;
	Map<String,String> pathValues ;
	RequestMembers requestObj = new RequestMembers();
	@Given("User has payload")
	public void user_has_payload() throws IOException {
		
	    // Write code here that turns the phrase above into concrete actions
		prop = loadPropertyFile();
		RestAssured.baseURI =prop.getProperty("baseURI");
		RequestSpecification reqSpec = RequestBuildSpec();
		request = given().spec(reqSpec);
		
		
	}
	
	@And("query param {string} as {string}")
	public void query_param_as(String queryKey, String queryValue) {
	    // Write code here that turns the phrase above into concrete actions
		request = request.queryParam(queryKey, queryValue);
		/*if(param.equalsIgnoreCase("query")) {
			queryValues.put(queryKey, queryValue);
		}
	 
		else if (param.equalsIgnoreCase("path")) {
			pathValues.put(queryKey, queryValue);
		}*/
	}


	@When("User calls {string} http request {string}")
	public void user_calls_http_request(String api, String apiName) {
		ResponseSpecification resSpec = ResponseBuildSpec();
		/*requestObj.sethttpCall(api);
		requestObj.setResource(apiName);
		requestObj.setQueryParams(pathValues);
		requestObj.setQueryParams(queryValues);
		requestObj.setContentType("JSON");*/
		if(api.equalsIgnoreCase("GET")) {
			 response = request.when().get(apiName).then().spec(resSpec).extract().response();
		}
	 
		else if(api.equalsIgnoreCase("POST")){
			response = request.when().post(apiName).then().spec(resSpec).extract().response();
		}
	  
	}




	@Then("verify the {string} is {int}")
	public void verify_the_is(String string, int code) {
	    // Write code here that turns the phrase above into concrete actions
		int statusCode = response.statusCode();
		assertEquals(statusCode, code);
	  
	}
	@Then("response body {string} is {string}")
	public void response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		responseJson = getJsonpath(response);
		String exepectedStatus = responseJson.get(key).toString();
		System.out.println("======="+exepectedStatus);
		assertEquals(value, exepectedStatus);
	    
	}

	@Then("response body list of products")
	public void response_body_list_of_products() {
	    // Write code here that turns the phrase above into concrete actions
		responseJson = getJsonpath(response);
		//method 1
		//ArrayList<Product> list=new Gson().fromJson(response.getBody().asString(),new ArrayList<Product>().getClass());
		//method 2
	    //JsonPath listProducts=JsonPath.with(response.asInputStream()).get("$");
		System.out.println(response.asString());
		//System.out.println(list.get(0));
		//System.out.println(listProducts.get(0));
	
		//System.out.println(listProducts.getString("name"));
	
	
	}
	
	



}
