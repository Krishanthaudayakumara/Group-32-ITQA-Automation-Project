package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class CheckoutPage extends PageObject {
    // Locator for the checkout page heading
    private By checkoutPageHeader = By.xpath("//li[text()='Checkout']");

    //locator for the product name in the checkout page
    private By productName = By.xpath("//div[@id='checkout-cart']//table/tbody/tr/td[@class='text-left']/a");
    private By productPrice = By.xpath("//div[@id='checkout-cart']//table/tbody/tr/td[@class='text-right'][2]");
    private By continueButton = By.id("button-save");
    private By termsAndConditionsCheckbox = By.xpath("//label[@for='input-agree']");
    private By telephoneField = By.id("input-telephone");
    private By billingAddressSection = By.id("payment-address");
    private By subTotal = By.xpath("//table[@id='checkout-total']/tbody/tr[1]/td[2]/strong");
    private By flatShipping = By.xpath("//table[@id='checkout-total']/tbody/tr[2]/td[2]/strong");
    private By ecoTax = By.xpath("//table[@id='checkout-total']/tbody/tr[3]/td[2]/strong");
    private By vat = By.xpath("//table[@id='checkout-total']/tbody/tr[4]/td[2]/strong");
    private By total = By.xpath("//table[@id='checkout-total']/tbody/tr[5]/td[2]/strong");

    public void verifyCheckoutPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPageHeader));
        waitFor(headerElement);
    }

    public String getProductName() {
        WebElement element = find(productName);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getProductPrice() {
        WebElement element = find(productPrice);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void clickContinueButton() {
        WebElement element = find(continueButton);
        waitForCondition().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean isTermsAndConditionsCheckboxVisible() {
        return find(termsAndConditionsCheckbox).isDisplayed();
    }

    public void checkTermsAndConditions(){
        WebElement element = find(termsAndConditionsCheckbox);
        waitForCondition().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
//        assertTrue("The checkbox is not checked.", element.isSelected());
    }

    public boolean isTelephoneFieldVisible() {
        return find(telephoneField).isDisplayed();
    }

    public boolean isBillingAddressSectionVisible() {
        return find(billingAddressSection).isDisplayed();
    }

    public String getSubTotal(){
        WebElement element = find(subTotal);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getFlatShipping(){
        WebElement element = find(flatShipping);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getEcoTax(){
        WebElement element = find(ecoTax);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getVat(){
        WebElement element = find(vat);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getTotal(){
        WebElement element = find(total);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

}