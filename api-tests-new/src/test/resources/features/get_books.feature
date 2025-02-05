Feature: Get books

  @getBooks
  Scenario: Admin gets the books
    Given The "admin" is authenticated
    When The admin sends the get API request
    Then The all available books should be retrieved successfully with status code as 200

  @getBooks
  Scenario: User gets the books
    Given The "user" is authenticated
    When The user sends the get API request
    Then The all available books should be retrieved successfully with status code as 200