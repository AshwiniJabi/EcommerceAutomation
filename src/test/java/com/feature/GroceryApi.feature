#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: validating grocery API

  Scenario: To check if the service is UP
    Given User has payload
    When User calls "GET" http request "status"
    Then verify the "status" is "200"
    And response body "status" is "UP"
  
 Scenario: Get all the products
    Given User has payload
    When User calls "GET" http request "products"
    Then verify the "status" is "200"
    And response body list of products
	    @Smoke  
   Scenario Outline: Get selected  products
    Given User has payload
    And query param "results" as "<results>"
    And query param "category" as "<category>"
    And query param "available" as "<available>"
    When User calls "GET" http request "products"
    Then verify the "status" is <statusCode>
   And response body list of products
     Examples:
     |results|category|available|statusCode|
     |3|dairy|true|200|
     |A|dairy|true|200|
     |19|daiyyry|true|400|
     
     
    @Smoke  
  Scenario Outline: Get selected  products
    Given User has payload
    And query param "results" as "<results>"
    And query param "category" as "<category>"
    When User calls "GET" http request "products"
    Then verify the "status" is <statusCode>
    And response body list of products
  Examples:
     |results|category|statusCode|
    	|2|dairy|200|
    	|2|daiiiry|400|
    
  Scenario Outline: Get selected  products
    Given User has payload
    When User calls "GET" http request "products" using filter "<available>" "<category>" 
    Then verify the "status" is "<statusCode>"
    And response body list of products
    
   Scenario: Get selected  products
    Given User has payload
    When User calls "GET" http request "products" using filter "<results>" "<available>"
    Then verify the "status" is <statusCode>
    And response body list of products
    
    
    @CreateCart
   Scenario Outline: Create Cart
   Given User has payload
   When  User calls "POST" http request "carts" 
   Then verify the "status" is 201
   And response body "created" is 'true'
   
   @GetCart
   Scenario Outline: Get Cart details
   Given User has payload
   And "path" param "cartId" as <cartId>
   When  User calls "GET" http request "carts" 
   Then verify the "status" is 201
   And response body list of products
   
   @AddItem
   Scenario Outline: Create Cart
   Given User has payload
   When  User calls "POST" http request "carts" 
   Then verify the "status" is 201
   And response body "created" is 'true'