package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends PageObject {
    private By productName = By.xpath("//form//table/tbody/tr/td[@class='text-left']/a");
    private By productPrice = By.xpath("//form//table/tbody/tr/td[@class='text-right'][2]");
    private By removeButton = By.xpath("//button[@class='btn btn-danger']");
    private By emptyCartMessage = By.xpath("//div[@id='content']/p[text()='Your shopping cart is empty!']");
    private By cartPageHeader = By.xpath("//li[text()='Shopping Cart']");


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
    public void removeProductFromCart() {
        WebElement remove = find(removeButton);
        waitForCondition().until(ExpectedConditions.elementToBeClickable(remove));
        remove.click();
        waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
    }

    public boolean isEmptyCartMessageDisplayed() {
        return find(emptyCartMessage).isDisplayed();
    }

    public void verifyShoppingCartPage() {
        WebElement headerElement = find(cartPageHeader);
        waitFor(headerElement);
        // assertTrue("The header of the shopping cart page is not displayed.", headerElement.isDisplayed());
        waitForCondition().until(ExpectedConditions.visibilityOf(headerElement));

    }
}