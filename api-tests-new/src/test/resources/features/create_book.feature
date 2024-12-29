Feature: Create a new book via the API

#Create Book By "admin"
  @createBook
  Scenario: Admin user creates a book
    Given the "admin" user is authorized
    When the "admin" user creates a book with title "Admin's Book" and author "Author A"
    Then the book should be created successfully with status code 201

#Create Book By "user"
  @createBook
  Scenario: User creates a book
    Given the "user" user is authorized
    When the "user" user creates a book with title "User's Book" and author "Author B"
    Then the book should be created successfully with status code 201
