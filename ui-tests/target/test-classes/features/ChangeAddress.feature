Feature: Update Address Book Entry

  Scenario: Update the first address in the Address Book
    Given the user is logged in for Address Book Change
    When the user navigates to the "Address Book" section for update address
    And the user clicks the "Edit" button for the first address
    And the user updates the address form
    Then the address should be successfully updated
