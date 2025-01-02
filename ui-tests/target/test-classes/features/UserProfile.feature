Feature: User Edit Profile

  Scenario: Successfully update all profile fields
    Given the user is logged in
    And the user navigates to the edit profile page
    When the user updates their profile with first name "John", last name "Doe", email "chinthanazc@example.com", and telephone "9876543210"
    Then a success message should be displayed
