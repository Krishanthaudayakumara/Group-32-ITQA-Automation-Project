Feature: User Edit Profile

  Scenario: Successfully update the last name in the profile
    Given the user is logged in
    And the user navigates to the edit profile page
    When the user updates the last name to "Janith"
    Then a success message should be displayed
