Feature: Proceed to Checkout from Cart

  Background:
    Given the user is logged in
    And the user is on the home page
    When the user selects a product from the product list
      And the user navigates to the product details page
    And the product is available
    And the user adds the product to the cart

  Scenario: Proceed to checkout
    When the user navigates to the cart page
    And the user proceeds to checkout
    Then user navigate to checkout page
    And the checkout page should display the correct product and price details
    And user pick existing address
    And the user accepts the terms and conditions
    And the user clicks the continue button
    Then the user should be able to see the confirm order page
    And the user confirms the order
    Then the user navigates to the success page
    And user click continue button