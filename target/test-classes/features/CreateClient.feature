@createClient
Feature: create a client

  Scenario: Validate the user can  create a client
    Given user hits base url "https://backend.cashwise.us"
    When  user provides valid authorization  "token"
    And user selects clients tag
    And user provides company name
    And user provides user name
    And user provides email
    And user provides phone number
    And user provides address
    And user provides id tag
    Then verify client was created
