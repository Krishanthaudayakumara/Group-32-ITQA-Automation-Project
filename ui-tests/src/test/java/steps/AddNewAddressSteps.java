package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.AddressBookPage;
import pages.LoginPage;

public class AddNewAddressSteps {

    Dotenv dotenv = Dotenv.load();

    @Steps
    LoginPage loginPage;

    @Steps
    AddressBookPage addressBookPage;

    @Given("the user is logged in for add new address")
    public void the_user_is_logged_in_for_add_new_address() {
        loginPage.openLoginPage();
        loginPage.enterEmail(dotenv.get("LOGIN_USERNAME"));
        loginPage.enterPassword(dotenv.get("LOGIN_PASSWORD"));
        loginPage.clickLoginButton();
        Assertions.assertThat(loginPage.getCurrentUrl())
                .as("Login failed, not on Account Dashboard")
                .contains("route=account/account");
    }

    @When("the user navigates to the {string} section for add new address")
    public void the_user_navigates_to_the_section(String section) {
        if (section.equalsIgnoreCase("Address Book")) {
            addressBookPage.navigateToAddressBook();
        }
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String button) {
        if (button.equalsIgnoreCase("New Address")) {
            addressBookPage.clickNewAddressButton();
        }
    }

    @When("the user fills out the address form")
    public void the_user_fills_out_the_address_form() {
        addressBookPage.fillAddressForm(
                "John",              // First Name
                "Doe",               // Last Name
                "Company Inc.",      // Company
                "123 Street Name",   // Address 1
                "Apartment 456",     // Address 2
                "Cityville",         // City
                "12345",             // Post Code
                "United Kingdom",    // Country
                "Greater London",    // Region/State
                true                 // Default Address
        );
    }

    @Then("the new address should be successfully added")
    public void the_new_address_should_be_successfully_added() {
        Assertions.assertThat(addressBookPage.getSuccessMessage())
                .as("Address was not successfully added")
                .isEqualTo("Your address has been successfully added");
    }
}
