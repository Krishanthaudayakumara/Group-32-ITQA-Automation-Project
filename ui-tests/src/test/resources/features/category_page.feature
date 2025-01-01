Feature: Category Page Testing

  # Test Case 1: Verify Navigation to the Category Page
  Scenario: User navigates to the category page successfully
    Given I am on the e-commerce homepage
    When I navigate to the "Cameras" category page
    Then I should see the category title as "Cameras"
    And the breadcrumb trail should display "Home > Cameras"
    And the page should load without any errors

  # Test Case 2: Verify Product Listing in the Category Page
  Scenario: Products are displayed correctly on the category page
    Given I am on the "Cameras" category page
    When I scroll through the product list
    Then each product should display the product name, image, and price
    And each product should have "Add to Cart", "Add to Wishlist", and "Compare" buttons
    And the total number of products should match the product count mentioned on the page

  # Test Case 3: Verify Product Filtering Functionality
  Scenario: Filter products by price range
    Given I am on the "Cameras" category page
    When I apply a filter for products priced under "$150"
    Then the product list should update to show only products under "$150"
    And no products above "$150" should be displayed

  # Test Case 4: Verify Pagination on the Category Page
  Scenario: Navigate through product pages
    Given I am on the "Cameras" category page with multiple pages
    When I click on "Next" to go to the next page
    Then the URL should update to show the new page
    And the products for the selected page should be displayed
    When I click on "Previous" to go back to the previous page
    Then the URL should update to show the previous page
    And the products for the previous page should be displayed

  # Test Case 5: Verify Add to Cart from the Category Page
  Scenario: Add a product to the cart
    Given I am on the "Cameras" category page
    When I click "Add to Cart" on a product
    Then the cart icon should update to reflect the added product
    And the product should be listed in the cart with the correct name, price, and quantity
