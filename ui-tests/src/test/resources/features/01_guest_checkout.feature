Feature: Guest Checkout Functionality

  Background:
    Given the user is not logged in
    And the user is on the home page
    When the user selects a product from the product list
    And the user navigates to the product details page
    And the product is available
    And the user adds the product to the cart
    And the user navigates to the cart page

  Scenario: Proceed to checkout as guest user
    When the user proceeds to checkout
    And the user chooses guest checkout
    Then user navigate to checkout page
    And the checkout page should display the correct product and price details
    And the user fills the all the checkout details
    And the user accepts the terms and conditions
    And the user clicks the continue button
    Then the user should be able to see the confirm order page
    And the user confirms the order
    Then the user navigates to the success page
    And user click continue button


  Scenario: Proceed to checkout as guest user with incorrect telephone number
    When the user proceeds to checkout
    And the user chooses guest checkout
    Then user navigate to checkout page
    And the checkout page should display the correct product and price details
    When the user fills the all the checkout details with incorrect telephone number
    Then an error message should be displayed "Invalid mobile number format."