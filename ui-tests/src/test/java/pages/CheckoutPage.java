package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class CheckoutPage extends BasePage {
    // Locator for the checkout page heading
    private By checkoutPageHeader = By.xpath("//li[text()='Checkout']");

    //locator for the product name in the checkout page
    private By productName = By.xpath("//div[@id='checkout-cart']//table/tbody/tr/td[@class='text-left']/a");
    private By productPrice = By.xpath("//div[@id='checkout-cart']//table/tbody/tr/td[@class='text-right'][2]");
    private By continueButton = By.id("button-save");
    private By termsAndConditionsCheckbox = By.xpath("//label[@for='input-agree']");
    private By telephoneField = By.id("input-payment-telephone");
    private By billingAddressSection = By.id("payment-address");
    private By subTotal = By.xpath("//table[@id='checkout-total']/tbody/tr[1]/td[2]/strong");
    private By flatShipping = By.xpath("//table[@id='checkout-total']/tbody/tr[2]/td[2]/strong");
    private By ecoTax = By.xpath("//table[@id='checkout-total']/tbody/tr[3]/td[2]/strong");
    private By vat = By.xpath("//table[@id='checkout-total']/tbody/tr[4]/td[2]/strong");
    private By total = By.xpath("//table[@id='checkout-total']/tbody/tr[5]/td[2]/strong");
    private By existingAddressLabel = By.xpath("//label[@for='input-payment-address-existing']");
    // Guest checkout
    private By guestCheckoutRadioButton = By.xpath("//label[@for='input-account-guest']");
    private By firstNameField = By.id("input-payment-firstname");
    private By lastNameField = By.id("input-payment-lastname");
    private By emailField = By.id("input-payment-email");
    private By companyField = By.id("input-payment-company");
    private By address1Field = By.id("input-payment-address-1");
    private By address2Field = By.id("input-payment-address-2");
    private By cityField = By.id("input-payment-city");
    private By postCodeField = By.id("input-payment-postcode");
    private By countryDropdown = By.id("input-payment-country");
    private By zoneDropdown = By.id("input-payment-zone");

    public CheckoutPage() {
        super();
    }

    public void verifyCheckoutPage() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(checkoutPageHeader));
    }

    public String getProductName() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(productName));
        return element.getText();
    }

    public String getProductPrice() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return element.getText();
    }

    public void clickContinueButton() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(continueButton));
        element.click();
    }

    public boolean isTermsAndConditionsCheckboxVisible() {
        handleAlerts();
        return find(termsAndConditionsCheckbox).isDisplayed();
    }

    public void checkTermsAndConditions(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox));
        element.click();
//        assertTrue("The checkbox is not checked.", element.isSelected());
    }

    public boolean isTelephoneFieldVisible() {
        handleAlerts();
        return find(telephoneField).isDisplayed();
    }

    public boolean isBillingAddressSectionVisible() {
        handleAlerts();
        return find(billingAddressSection).isDisplayed();
    }

    public String getSubTotal(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(subTotal));
        return element.getText();
    }
    public String getFlatShipping(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(flatShipping));
        return element.getText();
    }
    public String getEcoTax(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(ecoTax));
        return element.getText();
    }
    public String getVat(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(vat));
        return element.getText();
    }
    public String getTotal(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(total));
        return element.getText();
    }

    public void chooseGuestCheckout(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(guestCheckoutRadioButton));
        element.click();
    }

    public void enterFirstName(String firstName) {
        handleAlerts();
        $(firstNameField).type(firstName);
    }
    public void enterLastName(String lastName) {
        handleAlerts();
        $(lastNameField).type(lastName);
    }

    public void enterEmail(String email) {
        handleAlerts();
        $(emailField).type(email);
    }

    public void enterTelephone(String telephone) {
        handleAlerts();
        $(telephoneField).type(telephone);
    }
    public void enterCompany(String company) {
        handleAlerts();
        $(companyField).type(company);
    }
    public void enterAddress1(String address1) {
        handleAlerts();
        $(address1Field).type(address1);
    }
    public void enterAddress2(String address2) {
        handleAlerts();
        $(address2Field).type(address2);
    }
    public void enterCity(String city) {
        handleAlerts();
        $(cityField).type(city);
    }
    public void enterPostCode(String postCode) {
        handleAlerts();
        $(postCodeField).type(postCode);
    }

    public void selectCountry(String country) {
        handleAlerts();
        $(countryDropdown).selectByVisibleText(country);
    }
    public void selectZone(String zone) {
        handleAlerts();
        $(zoneDropdown).selectByVisibleText(zone);
    }

    public void selectExistingAddress(){
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(existingAddressLabel));
        element.click();
    }
}