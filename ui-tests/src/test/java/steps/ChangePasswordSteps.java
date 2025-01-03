package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.ChangePasswordPage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangePasswordSteps {
    Dotenv dotenv = Dotenv.load();

    @Steps
    LoginPage loginPage;
    @Steps
    LoginSteps loginSteps;

    ChangePasswordPage changePasswordPage;

    @Given("the user is logged in to change their password")
    public void userIsLoggedInToChangePassword() {
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

    @Then("an error message should be displayed for password {string}")
    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = changePasswordPage.getErrorMessage();
        assertThat(actualMessage)
                .withFailMessage("Expected error message '%s', but got '%s'", expectedMessage, actualMessage)
                .isEqualTo(expectedMessage);
    }
}

