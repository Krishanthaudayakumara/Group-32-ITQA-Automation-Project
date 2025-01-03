Feature: Validate whether mandatory parameters have given when book creation

  @validationError
  Scenario: Creating a book with an empty title
    Given the "user" user is authorized
    When the "user" attempts to create a book with title "" and author "Valid Author"
    Then the book should not be created and should return status code 400

  @validationError
  Scenario: Creating a book with an empty author
    Given the "admin" user is authorized
    When the "admin" attempts to create a book with title "Valid Title" and author ""
    Then the book should not be created and should return status code 400
