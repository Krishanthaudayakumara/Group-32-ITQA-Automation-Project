Feature: Create a Book with an Existing Title Via API

  @createExistingBook
  Scenario: Admin attempts to create a book with an existing title
    Given the "admin" can not able to create existing book
    When the "admin" tries to recreate a book with the title "Admin's Book 1" and author "Author A"
    Then system should return a status code "409"

  @createExistingBook
  Scenario: User attempts to create a book with an existing title
    Given the "user" can not able to create existing book
    When the "user" tries to recreate a book with the title "Admin's Book 1" and author "Author A"
    Then system should return a status code "409"