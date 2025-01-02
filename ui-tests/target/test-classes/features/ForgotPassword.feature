Feature: Change Password

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
