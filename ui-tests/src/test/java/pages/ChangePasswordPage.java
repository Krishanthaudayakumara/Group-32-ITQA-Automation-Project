package pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DefaultUrl("https://ecommerce-playground.lambdatest.io/index.php?route=account/password")
public class ChangePasswordPage extends PageObject {

    public static final Target CURRENT_PASSWORD_FIELD = Target.the("Current Password field")
            .located(By.id("input-password"));
    public static final Target NEW_PASSWORD_FIELD = Target.the("New Password field")
            .located(By.id("input-confirm"));
    public static final Target SUBMIT_BUTTON = Target.the("Submit button")
            .located(By.cssSelector("input[type='submit'].btn.btn-primary"));
    public static final Target SUCCESS_MESSAGE = Target.the("Success message")
            .located(By.cssSelector(".alert.alert-success.alert-dismissible"));

    public static final Target ERROR_MESSAGE = Target.the("Error message")
            .located(By.cssSelector(".text-danger"));

    public boolean isLoggedIn() {
        // Check if the user is logged in by verifying the presence of an account menu
        return find(By.cssSelector("nav.account-menu")).isPresent();
    }

    public void enterCurrentPassword(String currentPassword) {
        if (CURRENT_PASSWORD_FIELD.resolveFor(getDriver()).isVisible()) {
            CURRENT_PASSWORD_FIELD.resolveFor(getDriver()).type(currentPassword);
        } else {
            throw new IllegalStateException("Current Password field is not visible.");
        }
    }

    public void enterNewPassword(String newPassword) {
        if (NEW_PASSWORD_FIELD.resolveFor(getDriver()).isVisible()) {
            NEW_PASSWORD_FIELD.resolveFor(getDriver()).type(newPassword);
        } else {
            throw new IllegalStateException("New Password field is not visible.");
        }
    }

    public void submitPasswordChangeForm() {
        SUBMIT_BUTTON.resolveFor(getDriver()).click();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));
        return SUCCESS_MESSAGE.resolveFor(getDriver()).getText();
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE.resolveFor(getDriver()).getText();
    }
}
