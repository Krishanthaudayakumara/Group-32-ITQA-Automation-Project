package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private By productName = By.xpath("//form//table/tbody/tr/td[@class='text-left']/a");
    private By productPrice = By.xpath("//form//table/tbody/tr/td[@class='text-right'][2]");
    private By removeButton = By.xpath("//button[@class='btn btn-danger']");
    private By emptyCartMessage = By.xpath("//div[@id='content']/p[text()='Your shopping cart is empty!']");
    //locator to the 'Shopping Cart' text in the breadcrumb
    private By cartPageHeader = By.xpath("//li[text()='Shopping Cart']");
    private By checkoutButton = By.xpath("//a[contains(text(), 'Checkout')]");


    public CartPage() {
        super();
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
    public void removeProductFromCart() {
        handleAlerts();
        WebElement remove = waitForCondition().until(ExpectedConditions.elementToBeClickable(removeButton));
        remove.click();
        waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
    }

    public boolean isEmptyCartMessageDisplayed() {
        handleAlerts();
        return find(emptyCartMessage).isDisplayed();
    }

    public void verifyShoppingCartPage() {
        handleAlerts();
        WebElement headerElement = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(cartPageHeader));
    }
    public void proceedToCheckout() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(checkoutButton));
        element.click();
    }
}