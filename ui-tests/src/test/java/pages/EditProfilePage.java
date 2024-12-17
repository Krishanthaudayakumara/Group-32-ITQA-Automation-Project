package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class EditProfilePage extends PageObject {

    // Locators
    private By editProfileLink = By.linkText("Edit your account information");
    private By lastNameField = By.id("input-lastname");
    private By continueButton = By.cssSelector("input.btn.btn-primary");
    private By successAlert = By.cssSelector("div.alert.alert-success");

    // Methods
    public void navigateToEditProfilePage() {
        $(editProfileLink).click();
    }

    public void updateLastName(String lastName) {
        $(lastNameField).clear();
        $(lastNameField).type(lastName);
    }

    public void clickContinueButton() {
        $(continueButton).click();
    }

    public boolean isUpdateSuccessful() {
        return $(successAlert).isVisible();
    }
}
