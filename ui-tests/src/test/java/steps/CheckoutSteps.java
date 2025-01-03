package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.*;

public class CheckoutSteps {

    @Steps
    CartPage cartPage;

    @Steps
    CheckoutPage checkoutPage;

    @Steps
    ConfirmOrderPage confirmOrderPage;

    @Steps
    SuccessPage successPage;

    @Steps
    ProductPage productPage;



    private String productName;
    private String productPrice;
    private String productQuantity;
    private String productTotal;

    @When("the user click the buy now button")
    public void the_user_click_the_buy_now_button() {
        productPage.clickBuyNow();
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout(){
        cartPage.verifyShoppingCartPage();
        cartPage.proceedToCheckout();
    }

    @And("the user chooses guest checkout")
    public void the_user_chooses_guest_checkout() {
        checkoutPage.chooseGuestCheckout();
    }


    @And("the user fills the all the checkout details")
    public void the_user_fills_the_all_the_checkout_details() {
        checkoutPage.enterFirstName("Test");
        checkoutPage.enterLastName("User");
        checkoutPage.enterEmail("testuser@example.com");
        checkoutPage.enterTelephone("07712345678");
        checkoutPage.enterCompany("Test Company");
        checkoutPage.enterAddress1("Test Address 1");
        checkoutPage.enterAddress2("Test Address 2");
        checkoutPage.enterCity("Test City");
        checkoutPage.enterPostCode("ABC123");
        checkoutPage.selectCountry("United Kingdom");
        checkoutPage.selectZone("Lancashire");


    }
    @Then("user navigate to checkout page")
    public void user_navigate_to_checkout_page() {
        checkoutPage.verifyCheckoutPage();
    }

    @And("the checkout page should display the correct product and price details")
    public void the_checkout_page_should_display_the_correct_product_and_price_details() {
        productName = checkoutPage.getProductName();
        productPrice = checkoutPage.getProductPrice();
        Assertions.assertThat(checkoutPage.getProductName()).isEqualTo(productName);
        Assertions.assertThat(checkoutPage.getProductPrice()).isEqualTo(productPrice);
    }

    @And("the user accepts the terms and conditions")
    public void the_user_accepts_the_terms_and_conditions() {
        checkoutPage.checkTermsAndConditions();
    }

    @And("the user clicks the continue button")
    public void the_user_clicks_the_continue_button(){
        checkoutPage.clickContinueButton();
    }

    @Then("the user should be able to see the confirm order page")
    public void the_user_should_be_able_to_see_the_confirm_order_page(){
        confirmOrderPage.verifyConfirmOrderPage();
        productName = confirmOrderPage.getProductName();
        productPrice = confirmOrderPage.getProductPrice();
        productTotal = confirmOrderPage.getProductTotal();
        productQuantity = confirmOrderPage.getProductQuantity();
        Assertions.assertThat(confirmOrderPage.getShippingMethod()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getBillingAddress()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getShippingAddress()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getProductName()).isEqualTo(productName);
        Assertions.assertThat(confirmOrderPage.getProductPrice()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getProductQuantity()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getProductTotal()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getSubTotal()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getFlatShipping()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getEcoTax()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getVat()).isNotNull();
        Assertions.assertThat(confirmOrderPage.getTotal()).isNotNull();

    }

    @And("the user confirms the order")
    public void the_user_confirms_the_order(){
        confirmOrderPage.confirmOrder();
    }

    @Then("the user navigates to the success page")
    public void the_user_navigates_to_the_success_page(){
        Assertions.assertThat(successPage.isSuccessBreadcrumbPresent()).isTrue();
        Assertions.assertThat(successPage.issuccessMessagePresent()).isTrue();
    }

    @And("user click continue button")
    public void user_click_continue_button(){
        successPage.clickContinueButton();
    }



}