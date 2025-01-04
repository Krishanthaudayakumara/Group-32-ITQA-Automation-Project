package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.EditProfilePage;
import pages.LoginPage;

public class EditProfileSteps {
    Dotenv dotenv = Dotenv.load();

    @Steps
    LoginPage loginPage;

    @Steps
    LoginSteps loginSteps;

    @Steps
    EditProfilePage editProfilePage;

    @Given("the user is logged in for edit profile")
    public void the_user_is_logged_in() {
        // Check if the user is already logged in
        if (!loginPage.isLoggedIn()) {
            // If not logged in, perform the login process
            loginPage.openLoginPage();
            loginPage.enterEmail(dotenv.get("LOGIN_USERNAME"));
            loginPage.enterPassword(dotenv.get("LOGIN_PASSWORD"));
            loginPage.clickLoginButton();

            // Assert the login was successful by checking the URL or other indicators
            Assertions.assertThat(loginPage.getCurrentUrl())
                    .as("User login failed, not redirected to account dashboard.")
                    .contains("route=account/account");
        } else {
            System.out.println("User is already logged in. Skipping login process.");
        }
    }


    @And("the user navigates to the edit profile page")
    public void the_user_navigates_to_edit_profile_page() {
        editProfilePage.navigateToEditProfilePage();
    }

    @When("the user updates their profile with first name {string}, last name {string}, email {string}, and telephone {string}")
    public void the_user_updates_profile(String firstName, String lastName, String email, String telephone) {
        editProfilePage.updateFirstName(firstName);
        editProfilePage.updateLastName(lastName);
        editProfilePage.updateEmail(email);
        editProfilePage.updateTelephone(telephone);
        editProfilePage.clickContinueButton();
    }

    @Then("a success message should be displayed")
    public void a_success_message_should_be_displayed() {
        Assertions.assertThat(editProfilePage.isUpdateSuccessful())
                .withFailMessage("Expected success message is not displayed.")
                .isTrue();
    }

    @Then("an error message should be displayed {string}")
    public void an_error_message_should_be_displayed(String expectedErrorMessage) {
        Assertions.assertThat(editProfilePage.getErrorMessage())
                .as("Expected error message is not displayed.")
                .isEqualTo(expectedErrorMessage);
    }




}
