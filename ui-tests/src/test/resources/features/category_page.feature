Feature: Category Page Testing

  # Test Case 1: Verify Navigation to the Category Page
  Scenario: User navigates to the category page successfully
    Given I am on the e-commerce homepage
    When I Click Shop By Category
    And I navigate to the "Cameras" category page
    Then I should see the category title as "Cameras"
    And the breadcrumb trail should display "Cameras"
    And the page should load without any errors

  # Test Case 2: Verify Product Listing in the Category Page
  Scenario: Products are displayed correctly on the category page
    Given I am on the "Cameras" category page
    Then each product should display the product name, image, and price
    And the total number of products diaplayed should match the product count mentioned on the page

  # Test Case 3: Verify Product Filtering Functionality
  Scenario: Filter products by price range
    Given I am on the "Cameras" category page
    When I apply a filter for products priced above "$1000" and under "$15000"
    Then the product list should update to show only products under "$15000"
    And products above "$1000" should be displayed

  # Test Case 4: Verify Pagination on the Category Page
  Scenario: Navigate through product pages
    Given I am on the "Cameras" category page
    When I click on "Next" to go to the next page
    Then the URL should update to show the new page
    And the products for the selected page should be displayed
    When I click on "Previous" to go back to the previous page
    And the products for the previous page should be displayed

  # Test Case 5: Add to wishlist from the Category Page
  Scenario: Add a product to wishlist
    Given the user is on the login page
    And the user enters valid email and password
    And the user clicks the login button
    And the user should be redirected to the account dashboard
    And I am on the e-commerce homepage
    When I Click Shop By Category
    And I navigate to the "Cameras" category page
    When I click "Add to Wish List" on a product
    Then the wish list icon should update to reflect the added product
