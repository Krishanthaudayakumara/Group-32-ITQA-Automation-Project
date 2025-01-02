Feature: Add New Address

  Scenario: Add a new address to the address book
    Given the user is logged in for add new address
    When the user navigates to the "Address Book" section for add new address
    And the user clicks the "New Address" button
    And the user fills out the address form
    Then the new address should be successfully added

