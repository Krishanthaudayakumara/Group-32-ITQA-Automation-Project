Feature: Create a Book with an Existing Title

  Scenario: Admin attempts to create a book with an existing title
    Given the admin is authorized to create books
    When the admin tries to create a book with the title "Existing Book" that already exists
    Then the system should return a status code "409" for the admin
    And the system should display an error message "Book with the same title already exists" for the admin

  Scenario: User attempts to create a book with an existing title
    Given the user is authorized to create books
    When the user tries to create a book with the title "Existing Book" that already exists
    Then the system should return a status code "409" for the user
    And the system should display an error message "Book with the same title already exists" for the user
