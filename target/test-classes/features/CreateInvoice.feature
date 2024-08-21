Feature: creates a invoice

  Background:
    Given base url "https://backend.cashwise.us/api/myaccount"
@Invoice
  Scenario: user successfully creates a product
    And I have valid token
    And I have the endpoint "/invoices"
    And I have "invoice_title" with "Invoices" in request body
    And I have "client_id" with "2" in request body
    And I have "date_of_creation" with "2024-06-03" in request body
    And I have "end_date" with "2024-07-03" in request body
    And I have "products" with product
    And I have "sum" with "15.99" in request body
    And I have "discount" with "0" in request body
    And I have "sum_of_discount" with "0" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "invoice_title" with "Invoices" in response body

  @updateInvoice
  Scenario: Verify user can update invoice
    And I have access
    And I have the endpoint "/invoices"
    And I have "invoice_title" with "company invoice" in request body
    And I have "client_id" with "25" in request body
    And I have "date_of_creation" with "2024-06-03" in request body
    And I have "end_date" with "2024-06-29" in request body
    And I have "products" with product
    And I have "sum" with "15155" in request body
    And I have "discount" with "0" in request body
    And I have "sum_of_discount" with "15125" in request body
    When I send a POST request
    And I retrieve id for "invoice_id"
    Then verify status code is 201
    And I have "date_of_creation" with "2024-06-04" in request body
    And I have "end_date" with "2024-07-04" in request body
    And I have "invoice_title" with "Coffee Invoice" in request body
    And I have "products" with product
    When I send PUT request
    Then verify status code is 200