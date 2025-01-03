package steps;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;
import pages.LoginPage;

public class LoginSteps {

    private String username;
    private String password;

    public LoginSteps() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("LOGIN_USERNAME");
        this.password = dotenv.get("LOGIN_PASSWORD");
    }

    @Steps
    LoginPage loginPage;
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        if (!loginPage.isLoggedIn()) {
            loginPage.openLoginPage();
            loginPage.enterEmail(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            if(loginPage.isAlertPresent()){
                loginPage.handleAlert();
            }
            Assertions.assertThat(loginPage.getCurrentUrl()).contains("route=account/account");

        }
    }

    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        if (loginPage.isLoggedIn()) {
            loginPage.logout();
        }
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.openLoginPage();
    }

    @When("the user enters valid email and password")
    public void the_user_enters_valid_email_and_password() {
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the account dashboard")
    public void the_user_should_be_redirected_to_the_account_dashboard() {
        Assertions.assertThat(loginPage.getCurrentUrl()).contains("route=account/account");
    }
}