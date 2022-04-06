Feature: User endpoint test cases

  Scenario: User endpoint is executed
    Given an authorized request to the user endpoint
    And User rest API endpoint will reply with status 200 and data in user-rest-response
    When the endpoint is executed
    Then the request ended with http status 200
    And the body of the response contains users-request-response