Feature: User attempts to delete a book via the API

  @deleteBookByUser
  Scenario: User deletes an existing book
    Given user is not authorized to delete book
    When the user deletes a book with id 1
    Then the server should return status code 403 for unauthorized access for user
