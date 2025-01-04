Feature: Add Product to Cart

  Background:
    Given the user is logged in

  Scenario: Add a product to the cart from the product details page
    Given the user is on the home page
    When the user selects a product from the product list
    And the user navigates to the product details page
    And the product is available
    And the user adds the product to the cart
    And the user navigates to the cart page
    Then the product should appear in the cart with the correct details