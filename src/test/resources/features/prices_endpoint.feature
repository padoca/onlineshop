Feature: Prices endpoint test cases

  Scenario: Test 1
    Given an authorized request to the prices endpoint with date 2020-06-14-10.00.00
    When the endpoint is executed
    Then the request ended with http status 200
    And the price should have been the same price that with id 1

  Scenario: Test 2
    Given an authorized request to the prices endpoint with date 2020-06-14-16.00.00
    When the endpoint is executed
    Then the request ended with http status 200
    And the price should have been the same price that with id 1
    And the price should have been the same price that with id 2

  Scenario: Test 3
    Given an authorized request to the prices endpoint with date 2020-06-14-21.00.00
    When the endpoint is executed
    Then the request ended with http status 200
    And the price should have been the same price that with id 1

  Scenario: Test 4
    Given an authorized request to the prices endpoint with date 2020-06-15-10.00.00
    When the endpoint is executed
    Then the request ended with http status 200
    And the price should have been the same price that with id 1
    And the price should have been the same price that with id 3

  Scenario: Test 5
    Given an authorized request to the prices endpoint with date 2020-06-16-21.00.00
    When the endpoint is executed
    Then the request ended with http status 200
    And the price should have been the same price that with id 1
    And the price should have been the same price that with id 4
