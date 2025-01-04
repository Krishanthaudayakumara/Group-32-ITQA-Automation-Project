Feature: Update a book via the API

  @updateBook @positive
  Scenario: Admin updates an existing book successfully
    Given The "admin" user is authenticated for update
    When The "admin" user updates a book with id 1, title "Updated Book Title", and author "Updated Author"
    Then The book should be updated successfully with status code 200

  @updateBook @negative
  Scenario: User attempts to update a book and fails with forbidden error
    Given The "user" user is authenticated for update
    When The "user" user updates a book with id 1, title "New Title", and author "New Author"
    Then An error response with status code 403 should be returned

  @updateBook @negative
  Scenario: Admin updates a nonexistent book and receives not found error
    Given The "admin" user is authenticated for update
    When The "admin" user updates a book with id 9999, title "Nonexistent Book", and author "Unknown Author"
    Then An error response with status code 404 should be returned

  @updateBook @negative
  Scenario: Admin tries to update a book with an empty title
    Given The "admin" user is authenticated for update
    When The "admin" user updates a book with id 1, title "", and author "Author"
    Then An error response with status code 400 should be returned

  @updateBook @negative
  Scenario: Admin tries to update a book with an empty author
    Given The "admin" user is authenticated for update
    When The "admin" user updates a book with id 1, title "Valid Title", and author ""
    Then An error response with status code 400 should be returned

  @updateBook @negative
  Scenario: Admin updates a book with mismatched IDs
    Given The "admin" user is authenticated for update
    When The "admin" user updates a book with mismatched ids 1 and 3
    Then An error response with status code 400 should be returned
    And The response message should contain "ID mismatch"
