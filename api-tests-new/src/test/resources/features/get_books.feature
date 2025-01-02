Feature: Get books

  Scenario: Admin gets the books
    Given The admin is authenticated
    When The admin sends the get API request
    Then The available books should be retrieved successfully with status code as 200