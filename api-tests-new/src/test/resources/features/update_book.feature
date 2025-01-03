Feature: Update a book via the API

  @updateBook
  Scenario: Admin updates a book successfully
    Given the "admin" user is authenticated for update
    When the "admin" user updates a book with id 1, title "Updated Title", and author "Updated Author"

