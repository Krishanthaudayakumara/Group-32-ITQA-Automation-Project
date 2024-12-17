package steps;

import io.cucumber.java.en.*;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends PageObject {

    private static final Duration TIMEOUT = Duration.ofSeconds(10); // Maximum wait time

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.out.println("The user is on the login page");
        // Navigate to the login page
        openAt("https://aisurvey.netlify.app/login");
        // Wait until the username field is visible
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='username']")));
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        // Wait for the username field and enter data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='username']"))).sendKeys("user");
        // Wait for the password field and enter data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='password']"))).sendKeys("password");
        // Wait for the login button and click
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']"))).click();

        try {
            // Static wait to ensure redirection to the dashboard
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        // Wait for the URL to contain "/dashboard"
        wait.until(ExpectedConditions.urlContains("/home"));
        // Assert redirection to the dashboard
        assertThat(getDriver().getCurrentUrl()).contains("/home");
    }

    @When("the user enters invalid credentials")
    public void the_user_enters_invalid_credentials() {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        // Wait for the username field and enter invalid data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='username']"))).sendKeys("invalidUser");
        // Wait for the password field and enter invalid data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='password']"))).sendKeys("invalidPassword");
        // Wait for the login button and click
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']"))).click();
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        // Wait for the error message to be visible
        String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='error-message']"))).getText();
        // Assert the error message
        assertThat(errorMessage).isEqualTo("Invalid username or password.");
    }
}
