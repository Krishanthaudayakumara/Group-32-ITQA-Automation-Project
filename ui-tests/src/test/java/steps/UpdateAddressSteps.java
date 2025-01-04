package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.AddressBookPage;
import pages.LoginPage;

public class UpdateAddressSteps {

    Dotenv dotenv = Dotenv.load();

    @Steps
    LoginPage loginPage;

    @Steps
    AddressBookPage addressBookPage;

    @Given("the user is logged in for Address Book Change")
    public void the_user_is_logged_in_for_address_book_change() {
        if (!loginPage.isLoggedIn()) { // Check if the user is already logged in
            loginPage.openLoginPage();
            loginPage.enterEmail(dotenv.get("LOGIN_USERNAME"));
            loginPage.enterPassword(dotenv.get("LOGIN_PASSWORD"));
            loginPage.clickLoginButton();
            Assertions.assertThat(loginPage.getCurrentUrl())
                    .as("Login failed, not on Account Dashboard")
                    .contains("route=account/account");
        }
    }


    @When("the user navigates to the {string} section for update address")
    public void the_user_navigates_to_the_section(String section) {
        if (section.equalsIgnoreCase("Address Book")) {
            addressBookPage.navigateToAddressBook();
        }
    }

    @When("the user clicks the {string} button for the first address")
    public void the_user_clicks_the_button_for_the_first_address(String button) {
        if (button.equalsIgnoreCase("Edit")) {
            addressBookPage.clickEditFirstAddress();
        }
    }

    @When("the user updates the address form")
    public void the_user_updates_the_address_form() {
        addressBookPage.fillAddressForm(
                "Jane",              // Updated First Name
                "Smith",             // Updated Last Name
                "Updated Company",   // Updated Company
                "456 New Street",    // Updated Address 1
                "Suite 789",         // Updated Address 2
                "Updated City",      // Updated City
                "67890",             // Updated Post Code
                "United States",     // Updated Country
                "California",        // Updated Region/State
                false                // Default Address: No
        );
    }

    @Then("the address should be successfully updated")
    public void the_address_should_be_successfully_updated() {
        Assertions.assertThat(addressBookPage.getSuccessMessage())
                .as("Address was not successfully updated")
                .isEqualTo("Your address has been successfully updated");
    }
}
