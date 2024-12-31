package com.example.tests.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.junit.jupiter.api.Assertions;

public class CategoryPageSteps extends PageObject {

    @Given("I am on the e-commerce homepage")
    public void i_am_on_the_homepage() {
        openUrl("https://ecommerce-playground.lambdatest.io/");
    }

    @When("I navigate to the {string} category page")
    public void i_navigate_to_category_page(String category) {
        WebElementFacade categoryLink = $(By.linkText(category));
        Assertions.assertTrue(categoryLink.isVisible(), "Category link not visible");
        categoryLink.click();
    }

    @Then("I should see the category title as {string}")
    public void i_should_see_the_category_title(String categoryTitle) {
        WebElementFacade title = $(By.xpath("//h1[text()='" + categoryTitle + "']"));
        Assertions.assertTrue(title.isVisible(), "Category title not found!");
    }

    @Then("the breadcrumb trail should display {string}")
    public void breadcrumb_trail_display(String breadcrumb) {
        WebElementFacade breadcrumbTrail = $(By.cssSelector(".breadcrumb"));
        Assertions.assertTrue(breadcrumbTrail.isVisible(), "Breadcrumb trail not visible");
        Assertions.assertTrue(breadcrumbTrail.getText().contains(breadcrumb), "Breadcrumb trail is not as expected!");
    }

    @Then("the page should load without any errors")
    public void page_should_load_without_errors() {
        WebElementFacade pageContent = $(By.id("content"));
        Assertions.assertTrue(pageContent.isDisplayed(), "Page did not load correctly");
    }

    @Then("each product should display the product name, image, and price")
    public void each_product_should_display_info() {
        $$(".product-layout").forEach(product -> {
            Assertions.assertTrue(product.find(By.cssSelector(".product-title")).isVisible(), "Product name is not displayed");
            Assertions.assertTrue(product.find(By.cssSelector(".img-fluid")).isVisible(), "Product image is not displayed");
            Assertions.assertTrue(product.find(By.cssSelector(".price")).isVisible(), "Product price is not displayed");
        });
    }

    @Then("each product should have {string}, {string}, and {string} buttons")
    public void each_product_should_have_buttons(String addToCart, String addToWishlist, String compare) {
        $$(".product-layout").forEach(product -> {
            Assertions.assertTrue(product.find(By.xpath("//button[text()='" + addToCart + "']")).isVisible(), addToCart + " button not found");
            Assertions.assertTrue(product.find(By.xpath("//button[text()='" + addToWishlist + "']")).isVisible(), addToWishlist + " button not found");
            Assertions.assertTrue(product.find(By.xpath("//button[text()='" + compare + "']")).isVisible(), compare + " button not found");
        });
    }

    @Then("the total number of products should match the product count mentioned on the page")
    public void total_product_count_should_match() {
        WebElementFacade productCountElement = $(By.xpath("//div[@class='product-count']"));
        Assertions.assertTrue(productCountElement.isVisible(), "Product count not displayed");

        String countText = productCountElement.getText();
        int totalProductsDisplayed = $$(".product-layout").size();

        int totalProductsMentioned = Integer.parseInt(countText.split(" ")[0]);
        Assertions.assertEquals(totalProductsMentioned, totalProductsDisplayed, "Product count mismatch");
    }
}
