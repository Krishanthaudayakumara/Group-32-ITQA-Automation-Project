Feature: Create a new book via the API

  @createBook
  Scenario: Admin user creates a book
    Given the admin user is authorized
    When the admin creates a book with title "Jungle Book 1st Edition" and author "JK Rowling"
    Then the book should be created successfully with status code 201