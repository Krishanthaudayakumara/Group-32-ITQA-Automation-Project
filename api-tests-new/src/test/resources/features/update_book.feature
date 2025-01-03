Feature: Update a book via the API

  @updateBook
  Scenario: User is forbidden to update a book
    Given the "user" user is authenticated for update
    When the "user" user updates a book with id 1, title "Updated Title", and author "Updated Author"
    Then an error response with status code 403 should be returned

  @updateBook
  Scenario: Invalid book data causes an error
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 1, title "", and author "Updated Author"
    Then an error response with status code 400 should be returned
