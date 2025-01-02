Feature: User attempts to delete a book via the API

  # Scenario: User attempts to delete a book
  @deleteBook
  Scenario: User attempts to delete a book
    Given the user attempts to delete books
    When the user deletes a book with id 1
    Then the server should return status code 403 for unauthorized access
