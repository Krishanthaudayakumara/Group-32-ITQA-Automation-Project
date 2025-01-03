package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class
AddressBookPage extends PageObject {

    private By addressBookLink = By.cssSelector("a[href*='account/address']");
    private By newAddressButton = By.cssSelector("a.btn.btn-primary[href*='account/address/add']");
    private By editFirstAddressButton = By.cssSelector("table tbody tr:first-child a.btn.btn-info");
    private By firstAddressDeleteButton = By.cssSelector("table tbody tr:first-child a.btn.btn-danger");
    private By successAlert = By.cssSelector("div.alert.alert-success.alert-dismissible");
    private By firstNameField = By.id("input-firstname");
    private By lastNameField = By.id("input-lastname");
    private By companyField = By.id("input-company");
    private By address1Field = By.id("input-address-1");
    private By address2Field = By.id("input-address-2");
    private By cityField = By.id("input-city");
    private By postCodeField = By.id("input-postcode");
    private By countryDropdown = By.id("input-country");
    private By regionDropdown = By.id("input-zone");
    private By defaultAddressYesRadio = By.cssSelector("input[name='default'][value='1']");
    private By defaultAddressNoRadio = By.cssSelector("input[name='default'][value='0']");
    private By continueButton = By.cssSelector("input[type='submit'][value='Continue']");
    private By successMessage = By.cssSelector("div.alert.alert-success");

    public void navigateToAddressBook() {
        $(addressBookLink).click();
    }

    public void clickNewAddressButton() {
        $(newAddressButton).click();
    }

    public void clickEditFirstAddress() {
        $(editFirstAddressButton).click();
    }

    public void fillAddressForm(
            String firstName,
            String lastName,
            String company,
            String address1,
            String address2,
            String city,
            String postCode,
            String country,
            String region,
            boolean isDefault
    ) {
        $(firstNameField).type(firstName);
        $(lastNameField).type(lastName);
        $(companyField).type(company);
        $(address1Field).type(address1);
        $(address2Field).type(address2);
        $(cityField).type(city);
        $(postCodeField).type(postCode);

        // Wait for the country dropdown to be enabled
        WebElement countryElement = waitFor(ExpectedConditions.elementToBeClickable(countryDropdown));
        new Select(countryElement).selectByVisibleText(country);

        WebElement regionElement = waitFor(ExpectedConditions.elementToBeClickable(regionDropdown));
        new Select(regionElement).selectByVisibleText(region);

        if (isDefault) {
            $(defaultAddressYesRadio).click();
        } else {
            $(defaultAddressNoRadio).click();
        }

        $(continueButton).click();
    }


    public String getSuccessMessage() {
        return $(successMessage).getText();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void clickDeleteFirstAddress() {
        if (isAddressListEmpty()) {
            throw new IllegalStateException("No address available to delete.");
        }
        WebElement deleteButton = $(firstAddressDeleteButton).waitUntilEnabled();
        deleteButton.click();

        // Handle alert if present
        if (isAlertPresent()) {
            getAlert().accept(); // Accept the confirmation alert
        }
    }

    private boolean isAlertPresent() {
        try {
            getAlert();
            return true;
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isAddressListEmpty() {
        return findAll(By.cssSelector("table tbody tr")).isEmpty();
    }

    public String getDeleteSuccessMessage() {
        return $(successAlert).waitUntilVisible().getText();
    }

}
