Feature:Create a category
  @createACategory
  Scenario: user will create a category with providing numbers negative scenario
    Given user base url "https://backend.cashwise.us"
    Then the user will provide a valid token
    Then the user will provide name of the category with numbers
    Then the user provides the description of the category
    Then the user hits the api endpoint "/api/myaccount/categories"
    Then verify the status code 404

    @TestACategory
    Scenario: user will create a category with positive scenario
      Given user will provide base url "https://backend.cashwise.us"
      Then the user  provide a valid token
      Then the user will provide name of the category
      Then the user provides the description of  category
      Then the user hits  api endPoint "/api/myaccount/categories"
      Then verify the status code is  201
