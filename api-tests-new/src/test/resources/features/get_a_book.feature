Feature: Get a book

  @getABook
  Scenario: Admin get a book
    Given The "admin" is authenticated
    When The admin sends the get API request with 1
    Then The requested book 1 should be retrieved successfully with status code as 200

  @getABook
  Scenario: User get a book
    Given The "user" is authenticated
    When The user sends the get API request with 1
    Then The requested book 1 should be retrieved successfully with status code as 200

  @getABook
  Scenario: Admin tries to get a book which is not available
    Given The "admin" is authenticated
    When The admin sends the get API request with 1000
    Then The message as "Book not found" should be appeared
    And The status code should be retrieved as 404

  @getABook
  Scenario: Admin tries to get a book by passing a non integer value
    Given The "admin" is authenticated
    When The admin sends the get API request with "string"
    Then The response body should be empty with status code as 400

  @getABook
  Scenario: User tries to get a book by passing a non integer value
    Given The "user" is authenticated
    When The user sends the get API request with "string"
    Then The response body should be empty with status code as 400