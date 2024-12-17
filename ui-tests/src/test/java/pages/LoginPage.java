package pages;

import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private String baseUrl;
    // Locators
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.cssSelector("input.btn.btn-primary");


    public LoginPage() {
        Dotenv dotenv = Dotenv.load();
        this.baseUrl = dotenv.get("SERENITY_BASE_URL");
    }
    // Methods
    public void openLoginPage() {
        openAt(baseUrl+"?route=account/login");
    }

    public void enterEmail(String email) {
        $(emailField).type(email);
    }

    public void enterPassword(String password) {
        $(passwordField).type(password);
    }

    public void clickLoginButton() {
        $(loginButton).click();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
