Feature: Buy Now and Checkout

  Background:
    Given the user is logged in
    And the user is on the home page
    When the user selects a product from the product list
    And the user navigates to the product details page
    And the product is available

  Scenario: Proceed to checkout by Buy Now
    When the user click the buy now button
    Then user navigate to checkout page
    And the checkout page should display the correct product and price details
    And the user accepts the terms and conditions
    And the user clicks the continue button
    Then the user should be able to see the confirm order page
    And the user confirms the order
    Then the user navigates to the success page
    And user click continue button