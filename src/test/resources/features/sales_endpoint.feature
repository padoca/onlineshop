Feature: Sales endpoint test cases

  Background:
    Given the database is populated with sales-test-cases

  Scenario: Sales endpoint is executed without user
    Given an authorized request to the sales endpoint with user null
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales endpoint is executed with a non-existent user
    Given an authorized request to the sales endpoint with user 65
    And User rest API endpoint will reply with status 200 and data in user-rest-response
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales endpoint is executed with a non-existent sale
    Given an authorized request to the sales endpoint with user 3
    When the endpoint is executed
    Then the request ended with http status 200
    And the body of the response contains empty-response

  Scenario: Sales endpoint is executed
    Given an authorized request to the sales endpoint with user 2
    And User rest API endpoint will reply with status 200 and data in user-rest-response
    When the endpoint is executed
    Then the request ended with http status 200
    And the body of the response contains sales-request-response

  Scenario: Sales byproduct endpoint is executed without product
    Given an authorized request to the sales byproduct endpoint with user 3 and product null
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales byproduct endpoint is executed with a non-existent sale
    Given an authorized request to the sales byproduct endpoint with user 3 and product 35455
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales byproduct endpoint is executed with a non-existent user
    Given an authorized request to the sales byproduct endpoint with user 65 and product 35455
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales byproduct endpoint is executed with a non-existent product
    Given an authorized request to the sales byproduct endpoint with user 1 and product 5
    When the endpoint is executed
    Then the request ended with http status 400

  Scenario: Sales byproduct endpoint is executed
    Given an authorized request to the sales byproduct endpoint with user 2 and product 1
    And User rest API endpoint will reply with status 200 and data in user-rest-response
    When the endpoint is executed
    Then the request ended with http status 200
    And the body of the response contains sale-request-response

