Feature: Update a book via the API

  @updateBook @positive
  Scenario: Admin updates an existing book successfully
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 1, title "Updated Book Title", and author "Updated Author"
    Then the book should be updated successfully with status code 200

  @updateBook @negative
  Scenario: User attempts to update a book and fails with forbidden error
    Given the "user" user is authenticated for update
    When the "user" user updates a book with id 1, title "New Title", and author "New Author"
    Then an error response with status code 403 should be returned

  @updateBook @negative
  Scenario: Admin updates a nonexistent book and receives not found error
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 9999, title "Nonexistent Book", and author "Unknown Author"
    Then an error response with status code 404 should be returned

  @updateBook @negative
  Scenario: Admin tries to update a book with invalid input
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 1, title "", and author "Author"
    Then an error response with status code 400 should be returned

  @updateBook @negative
  Scenario: Admin updates a book with mismatched IDs
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 1, title "Valid Title", and author "Valid Author"
    And the request body contains id 2
    Then an error response with status code 400 should be returned
    And the response message should contain "ID mismatch"
