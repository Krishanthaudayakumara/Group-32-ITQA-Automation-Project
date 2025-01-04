package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.AddressBookPage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteAddressSteps {
    Dotenv dotenv = Dotenv.load();

    @Steps
    LoginPage loginPage;

    @Steps
    AddressBookPage addressBookPage;

    @Given("the user is logged in for Address Book Delete")
    public void the_user_is_logged_in_for_address_book_delete() {
        if (!loginPage.isLoggedIn()) {
            loginPage.openLoginPage();
            loginPage.enterEmail(dotenv.get("LOGIN_USERNAME"));
            loginPage.enterPassword(dotenv.get("LOGIN_PASSWORD"));
            loginPage.clickLoginButton();
            Assertions.assertThat(loginPage.getCurrentUrl())
                    .as("User login failed, not redirected to account dashboard.")
                    .contains("route=account/account");
        }
    }


    @When("the user navigates to the {string} section")
    public void the_user_navigates_to_the_section(String section) {
        if (section.equalsIgnoreCase("Address Book")) {
            addressBookPage.navigateToAddressBook();
            assertThat(loginPage.getCurrentUrl())
                    .as("Failed to navigate to Address Book section")
                    .contains("route=account/address");
        }
    }


    @When("the user deletes the first address")
    public void the_user_deletes_the_first_address() {
        if (addressBookPage.isAddressListEmpty()) {
            throw new IllegalStateException("No addresses available to delete in the Address Book.");
        }
        addressBookPage.clickDeleteFirstAddress();
    }


    @Then("a success delete message should be displayed")
    public void a_success_message_should_be_displayed() {
        String expectedMessage = "Your address has been successfully deleted";
        String actualMessage = addressBookPage.getDeleteSuccessMessage();
        assertThat(actualMessage).contains(expectedMessage);
    }
}
