Feature: User Workflow

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid email and password
    And the user clicks the login button
    Then the user should be redirected to the account dashboard

  Scenario: Add a new address to the address book
    Given the user is logged in
    When the user navigates to the "Address Book" section for add new address
    And the user clicks the "New Address" button
    And the user fills out the address form
    Then the new address should be successfully added

  Scenario: Update the first address in the Address Book
    Given the user is logged in for Address Book Change
    When the user navigates to the "Address Book" section for update address
    And the user clicks the "Edit" button for the first address
    And the user updates the address form
    Then the address should be successfully updated


  Scenario: Delete the first address from the address book
    Given the user is logged in for Address Book Delete
    When the user navigates to the "Address Book" section
    And the user deletes the first address
    Then a success delete message should be displayed


  Scenario: Successfully change the user password
    Given the user is logged in to change their password
    And the user navigates to the change password page
    When the user enters the current password "Pass@123"
    And the user enters a new password "Pass@123"
    And the user submits the password change form
    Then a success message should be displayed "Your password has been successfully updated"


  Scenario: Failed password change due to incorrect current password
    Given the user is logged in to change their password
    And the user navigates to the change password page
    When the user enters the current password "WrongPassword123"
    And the user enters a new password "NewPassword123"
    And the user submits the password change form
    Then an error message should be displayed "Password confirmation does not match password!"


  Scenario: Successfully update all profile fields
    Given the user is logged in for edit profile
    And the user navigates to the edit profile page
    When the user updates their profile with first name "John", last name "Doe", email "chinth@example.com", and telephone "9876543210"
    Then a success message should be displayed

