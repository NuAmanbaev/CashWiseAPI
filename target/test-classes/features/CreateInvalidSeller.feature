
@SellerTest
Feature: user unable to create seller with invalid phone number
  Scenario: User can not create a seller with invalid phonenumber
    Given base url "https://backend.cashwise.us"
    When I provide  authorization token
    Then I provide company name "Bigg Service"
    Then I provide seller name "Stevenn Doez"
    Then I provide seller email "steeee@gmailcom"
    Then I provide seller invalid phonenumber "32111222"
    Then I provide seller address "12 W Devon Chicago"
    Then I hit POST endpoint "https://backend.cashwise.us/api/myaccount/sellers"
    Then I verify status Code is 400
