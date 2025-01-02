package steps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import pages.ChangePasswordPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangePasswordSteps {

    @Steps
    LoginSteps loginSteps;

    ChangePasswordPage changePasswordPage;

    @Given("the user is logged in to change their password")
    public void userIsLoggedInToChangePassword() {
        loginSteps.the_user_is_on_the_login_page();
        loginSteps.the_user_enters_valid_email_and_password();
        loginSteps.the_user_clicks_the_login_button();
    }

    @Given("the user navigates to the change password page")
    public void navigateToChangePasswordPage() {
        changePasswordPage.open();
    }

    @When("the user enters the current password {string}")
    public void enterCurrentPassword(String currentPassword) {
        changePasswordPage.enterCurrentPassword(currentPassword);
    }

    @When("the user enters a new password {string}")
    public void enterNewPassword(String newPassword) {
        changePasswordPage.enterNewPassword(newPassword);
    }

    @When("the user submits the password change form")
    public void submitPasswordChangeForm() {
        changePasswordPage.submitPasswordChangeForm();
    }

    @Then("a success message should be displayed {string}")
    public void verifySuccessMessage(String expectedMessage) {
        String actualMessage = changePasswordPage.getSuccessMessage();
        assertThat(actualMessage).contains(expectedMessage); // Use contains in case of additional text
    }

    @Then("an error message should be displayed {string}")
    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = changePasswordPage.getErrorMessage();
        assertThat(actualMessage)
                .withFailMessage("Expected error message '%s', but got '%s'", expectedMessage, actualMessage)
                .isEqualTo(expectedMessage);
    }
}
