Feature: Remove Product from Cart

  Background:
    Given the user is logged in
    And the user is on the home page
    When the user selects a product from the product list
    And the user navigates to the product details page
    And the product is available
    And the user adds the product to the cart
    And the user navigates to the cart page

  Scenario: Remove a product from the cart
    When the user removes the product from the cart
    Then the product should no longer be displayed in the cart