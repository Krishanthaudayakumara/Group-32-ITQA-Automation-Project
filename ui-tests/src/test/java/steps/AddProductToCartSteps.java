package steps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class AddProductToCartSteps {

    private String productName;
    private String productPrice;
    @Steps
    LoginPage loginPage;

    @Steps
    HomePage homePage;

    @Steps
    ProductPage productPage;

    @Steps
    CartPage cartPage;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        homePage.openHomePage();
    }

    @When("the user selects a product from the product list")
    public void the_user_selects_a_product_from_the_product_list() {
        productName = homePage.selectProductByName("HTC Touch HD");
    }
    @And("the user navigates to the product details page")
    public void the_user_navigates_to_the_product_details_page(){
        homePage.navigateToProductPage(productName);
    }

    @And("the product is available")
    public void the_product_is_available() {
        Assertions.assertThat(productPage.checkAvailability()).isTrue();
        productPrice = productPage.getPrice();
    }

    @And("the user adds the product to the cart")
    public void the_user_adds_the_product_to_the_cart() {
        productPage.addToCart();
        productPage.clickViewCart();
    }


    @Then("the product should appear in the cart with the correct details")
    public void the_product_should_appear_in_the_cart_with_the_correct_details() {
        Assertions.assertThat(cartPage.getProductName()).isEqualTo(productName);
        Assertions.assertThat(cartPage.getProductPrice()).isEqualTo(productPrice);
    }
}