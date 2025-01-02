Feature: Get a book
    
  Scenario: Admin get a book
    Given The "admin" is authenticated
    When The "admin" sends the get API request with 1
    Then The requested book should be retrieved successfully with status code as 200

  Scenario: User get a book
    Given The "user" is authenticated
    When The "user" sends the get API request with 1
    Then The requested book should be retrieved successfully with status code as 200

  Scenario: Admin tries to get a book which is not available
    Given The "admin" is authenticated
    When The "admin" sends the get API request with 10
    Then The error message as "Book not found" should be appeared with the status code as 404

  Scenario: Admin tries to get a book by passing a non integer Id
    Given The "admin" is authenticated
    When The "admin" sends the get API request with "string"
    Then The response body should be empty string with status code as 400