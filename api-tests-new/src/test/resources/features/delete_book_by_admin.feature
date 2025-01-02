Feature: Admin deletes a book via the API

  # Scenario 1: Admin deletes an existing book
  @deleteBook
  Scenario: Admin deletes an existing book
    Given the admin user is authorized to delete books
    When the admin deletes a book with id 1
    Then the book should be deleted successfully with status code 200

  # Scenario 2: Admin attempts to delete a non-existent book
  @deleteBook
  Scenario: Admin attempts to delete a non-existent book
    Given the admin user is authorized to delete books
    When the admin deletes a book with id 9999
    Then the server should return status code 404 if the book does not exist
