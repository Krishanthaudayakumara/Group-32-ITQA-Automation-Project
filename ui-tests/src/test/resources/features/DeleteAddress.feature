Feature: Delete Address

  Scenario: Delete the first address from the address book
    Given the user is logged in for Address Book Delete
    When the user navigates to the "Address Book" section
    And the user deletes the first address
    Then a success delete message should be displayed
