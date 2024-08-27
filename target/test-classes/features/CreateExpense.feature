@SellerTest
Feature: Create a new expense product

  Scenario: Successfully create a new expense product and verify status code 201
    Given I have a valid token
    And I generate random product details
    When I send a POST request to create the product
    Then the response status code should be 201
    And the response body should contain the same product title
    And the product price should be greater than 0