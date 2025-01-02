package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class EditProfilePage extends PageObject {

    // Locators for all fields
    private By editProfileLink = By.linkText("Edit your account information");
    private By firstNameField = By.id("input-firstname");
    private By lastNameField = By.id("input-lastname");
    private By emailField = By.id("input-email");
    private By telephoneField = By.id("input-telephone");
    private By continueButton = By.cssSelector("input.btn.btn-primary");
    private By successAlert = By.cssSelector("div.alert.alert-success");

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
        return $(successAlert).isVisible();
    }
}
