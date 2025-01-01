package com.example.tests.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import com.example.tests.pages.CategoryPage;

public class CategoryPageSteps {

    private CategoryPage categoryPage = new CategoryPage();

    @Given("I am on the e-commerce homepage")
    public void i_am_on_the_homepage() {
        categoryPage.openHomepage();
    }

    @When("I navigate to the {string} category page")
    public void i_navigate_to_category_page(String category) {
        Assertions.assertTrue(categoryPage.isCategoryVisible(category), "Category link not visible");
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

    @Then("the total number of products should match the product count mentioned on the page")
    public void total_product_count_should_match() {
        Assertions.assertTrue(categoryPage.isProductCountMatching(), "Product count mismatch");
    }
}
