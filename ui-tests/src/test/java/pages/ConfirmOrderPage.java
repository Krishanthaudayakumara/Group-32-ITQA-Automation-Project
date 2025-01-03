package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmOrderPage extends PageObject {

    private By confirmOrderHeader = By.xpath("//h1[text()='Confirm Order']");
    private By productName = By.xpath("//table//tbody/tr/td[@class='text-left'][1]");
    private By productModel = By.xpath("//table//tbody/tr/td[@class='text-left'][2]");
    private By productQuantity = By.xpath("//table//tbody/tr/td[@class='text-right'][1]");
    private By productPrice = By.xpath("//table//tbody/tr/td[@class='text-right'][2]");
    private By productTotal = By.xpath("//table//tbody/tr/td[@class='text-right'][3]");
    private By subTotal = By.xpath("//table//tfoot/tr[1]/td[2]");
    private By flatShipping = By.xpath("//table//tfoot/tr[2]/td[2]");
    private By ecoTax = By.xpath("//table//tfoot/tr[3]/td[2]");
    private By vat = By.xpath("//table//tfoot/tr[4]/td[2]");
    private By total = By.xpath("//table//tfoot/tr[5]/td[2]");
    private By billingAddress = By.xpath("//div[h4[contains(text(), 'Payment Address')]]/div[@class='card mb-4']/div[@class='card-body']");
    private By shippingAddress = By.xpath("//div[h4[contains(text(), 'Shipping Address')]]/div[@class='card mb-4']/div[@class='card-body']");
    private By shippingMethod = By.xpath("//div[h4[contains(text(), 'Shipping Method:')]]/div[@class='card mb-4']/div[@class='card-body']");
    private By confirmOrderButton = By.id("button-confirm");

    public void verifyConfirmOrderPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmOrderHeader));
        waitFor(headerElement);
    }

    public String getProductName() {
        WebElement element = find(productName);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getProductModel() {
        WebElement element = find(productModel);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getProductQuantity() {
        WebElement element = find(productQuantity);
//        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getProductPrice() {
        WebElement element = find(productPrice);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getProductTotal() {
        WebElement element = find(productTotal);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    public String getSubTotal() {
        WebElement element = find(subTotal);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    public String getFlatShipping() {
        WebElement element = find(flatShipping);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getEcoTax() {
        WebElement element = find(ecoTax);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getVat() {
        WebElement element = find(vat);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getTotal() {
        WebElement element = find(total);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    public String getBillingAddress() {
        WebElement element = find(billingAddress);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    public String getShippingAddress() {
        WebElement element = find(shippingAddress);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getShippingMethod() {
        WebElement element = find(shippingMethod);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    public void confirmOrder() {
        WebElement element = find(confirmOrderButton);
        waitForCondition().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}