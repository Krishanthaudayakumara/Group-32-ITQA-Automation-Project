package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.CategoryPage;

public class CategoryPageSteps {

    private CategoryPage categoryPage = new CategoryPage();

    @Given("I am on the e-commerce homepage")
    public void i_am_on_the_homepage() {
        categoryPage.openHomepage();
    }

    @When("I Click Shop By Category")
    public void i_click_shop_by_category () {
        categoryPage.clickShopByCategory();
    }

    @When("I navigate to the {string} category page")
    public void i_navigate_to_category_page(String category) {
        categoryPage.clickCategory(category);
    }

    @When("I am on the {string} category page")
    public void i_am_on_the_category_page(String category) {
        categoryPage.clickShopByCategory();
        categoryPage.clickCategory(category);
    }

    @Then("I should see the category title as {string}")
    public void i_should_see_the_category_title(String categoryTitle) {
        Assertions.assertTrue(categoryPage.isCategoryTitleVisible(categoryTitle), "Category title not found!");
    }

    @Then("the breadcrumb trail should display {string}")
    public void breadcrumb_trail_display(String breadcrumb) {
        Assertions.assertTrue(categoryPage.isBreadcrumbVisible(), "Breadcrumb trail not visible");
        Assertions.assertTrue(categoryPage.getBreadcrumbText().contains(breadcrumb), "Breadcrumb trail is not as expected!");
    }

    @Then("the page should load without any errors")
    public void page_should_load_without_errors() {
        Assertions.assertTrue(categoryPage.isPageContentVisible(), "Page did not load correctly");
    }

    @Then("each product should display the product name, image, and price")
    public void each_product_should_display_info() {
        Assertions.assertTrue(categoryPage.areAllProductsDisplayingInfo(), "Not all products display required information");
    }

    @Then("each product should have {string}, {string}, and {string} buttons")
    public void each_product_should_have_buttons(String addToCart, String addToWishlist, String compare) {
        Assertions.assertTrue(categoryPage.doAllProductsHaveButtons(addToCart, addToWishlist, compare),
                "One or more products are missing required buttons");
    }

    @Then("the total number of products diaplayed should match the product count mentioned on the page")
    public void total_product_count_should_match() {
        Assertions.assertTrue(categoryPage.isProductCountMatching(), "Product count mismatch");
    }

    @When("I apply a filter for products priced above {string} and under {string}")
    public void iApplyAFilterForProductsPricedUnder(String maxPrice, String minPrice ) {
        categoryPage.applyPriceFilter(maxPrice,minPrice);
    }

    @Then("the product list should update to show only products under {string}")
    public void theProductListShouldUpdateToShowOnlyProductsUnder(String price) {
        // Verify that all displayed products are under the specified price
        Assertions.assertTrue(categoryPage.areProductsUnderPrice(price), "Some products exceed the filter price");
    }

    @Then("products above {string} should be displayed")
    public void noProductsAboveShouldBeDisplayed(String price) {
        // Verify that no product prices are above the specified price
        Assertions.assertTrue(categoryPage.areNoProductsAbovePrice(price), "Some products exceed the filter price");
    }

    @When("I click on {string} to go to the next page")
    public void iClickOnNextToGoToNextPage(String button) {
        if (button.equals("Next")) {
            categoryPage.clickNextPage();  // Click on the Next button
        }
    }

    @Then("the URL should update to show the new page")
    public void theURLShouldUpdateToShowTheNewPage() {
        String currentUrl = categoryPage.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("page=2"), "The URL did not update correctly to the new page");
    }

    @Then("the products for the selected page should be displayed")
    public void theProductsForTheSelectedPageShouldBeDisplayed() {
        Assertions.assertTrue(categoryPage.areProductsDisplayed(), "No products are displayed on the selected page");
    }

    @When("I click on {string} to go back to the previous page")
    public void iClickOnPreviousToGoBackToPreviousPage(String button) {
        if (button.equals("Previous")) {
            categoryPage.clickPreviousPage();  // Click on the Previous button
        }
    }

    @Then("the products for the previous page should be displayed")
    public void theProductsForThePreviousPageShouldBeDisplayed() {
        Assertions.assertTrue(categoryPage.areProductsDisplayed(), "No products are displayed on the previous page");
    }

    @When("I click \"Add to Wish List\" on a product")
    public void iClickWishListOnAProduct() {
        categoryPage.addProductToWishList();
    }

    @Then("the wish list icon should update to reflect the added product")
    public void theWishListIconShouldUpdateToReflectTheAddedProduct() {
        Assertions.assertTrue(categoryPage.isWishListUpdated(), "The Wish List icon was not updated");
    }



}
