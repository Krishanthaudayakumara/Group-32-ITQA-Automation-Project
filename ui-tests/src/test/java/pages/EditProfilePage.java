package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditProfilePage extends PageObject {

    // Locators for all fields
    private By editProfileLink = By.linkText("Edit your account information");
    private By firstNameField = By.id("input-firstname");
    private By lastNameField = By.id("input-lastname");
    private By emailField = By.id("input-email");
    private By telephoneField = By.id("input-telephone");
    private By continueButton = By.cssSelector("input.btn.btn-primary");
    private By successAlert = By.cssSelector("div.alert.alert-success.alert-dismissible");
    private By errorAlert = By.cssSelector("div.alert.alert-danger.alert-dismissible");
    // Methods to interact with the page
    public void navigateToEditProfilePage() {
        $(editProfileLink).click();
    }

    public void updateFirstName(String firstName) {
        $(firstNameField).clear();
        $(firstNameField).type(firstName);
    }

    public void updateLastName(String lastName) {
        $(lastNameField).clear();
        $(lastNameField).type(lastName);
    }

    public void updateEmail(String email) {
        $(emailField).clear();
        $(emailField).type(email);
    }

    public void updateTelephone(String telephone) {
        $(telephoneField).clear();
        $(telephoneField).type(telephone);
    }

    public void clickContinueButton() {
        $(continueButton).click();
    }

    public boolean isUpdateSuccessful() {
        System.out.println("Checking if success message is displayed...");
        try {
            // Wait until the success message is visible
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));

            // Verify if the message contains the expected text
            String expectedMessage = "Success: Your account has been successfully updated.";
            String actualMessage = successMessage.getText().trim();

            System.out.println("Success message text: " + actualMessage);

            if (actualMessage.contains(expectedMessage)) {
                System.out.println("Success message is correctly displayed.");
                return true;
            } else {
                System.out.println("Success message text does not match expected.");
                return false;
            }
        } catch (TimeoutException e) {
            System.out.println("Success message did not appear within the timeout.");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Success message not found.");
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger")));
            return errorMessage.getText().trim();
        } catch (TimeoutException e) {
            System.out.println("Error message did not appear within the timeout.");
            return "";
        }
    }


}
