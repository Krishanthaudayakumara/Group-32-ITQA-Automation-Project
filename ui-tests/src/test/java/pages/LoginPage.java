package pages;

import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PageObject {

    private String baseUrl;
    // Locators
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.cssSelector("input.btn.btn-primary");
    private By accountDashboardHeader = By.xpath("//h2[contains(text(),'My Account')]");


    public LoginPage() {
        Dotenv dotenv = Dotenv.load();
        this.baseUrl = dotenv.get("SERENITY_BASE_URL");
    }
    // Methods
    public void openLoginPage() {
        openAt(baseUrl+"?route=account/login");
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        $(emailElement).type(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        $(passwordElement).type(password);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        $(loginElement).click();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public boolean isLoggedIn() {
        openLoginPage();
        //Check for alerts and dismiss before continuing
        if(isAlertPresent()){
            handleAlert();
        }
        //Use an explicit wait to ensure the element exists before checking its visibility
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.or(
                            ExpectedConditions.urlContains("route=account/account"),
                            ExpectedConditions.visibilityOfElementLocated(accountDashboardHeader)
                    )
            );
            return getCurrentUrl().contains("route=account/account") || $(accountDashboardHeader).isVisible();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false; //Timeout error indicates the user is not logged in
        }
    }

    public void logout() {
        openAt(baseUrl+"?route=account/logout");
    }
    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    public void handleAlert() {
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            //If no alert is present, carry on
            System.out.println("No alert was present");
        }
    }
    private void refreshPage() {
        getDriver().navigate().refresh();
    }
}