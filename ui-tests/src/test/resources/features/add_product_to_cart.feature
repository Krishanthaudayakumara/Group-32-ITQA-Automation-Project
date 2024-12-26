Feature: Add Product to Cart

  Background:
    Given the user is on the login page
    When the user enters valid email and password
    And the user clicks the login button
    Then the user should be redirected to the account dashboard

  Scenario: Add a product to the cart from the product details page
    Given the user is on the home page
    When the user selects a product from the product list
    And the user navigates to the product details page
    And the product is available
    And the user adds the product to the cart
    Then the product should appear in the cart with the correct details