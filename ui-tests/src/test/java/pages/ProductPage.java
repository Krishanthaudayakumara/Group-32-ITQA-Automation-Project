package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ProductPage extends BasePage {

    private By availability = By.xpath("//span[text()='In Stock']");
    private By price = By.cssSelector("h3.price-new");
    private By addToCartButton = By.cssSelector("#entry_216842 button.text.btn.btn-md.btn-secondary.btn-block.btn-cart.button-cart.cart-103");
    private By buyNowButton = By.cssSelector("#entry_216843 button.text.btn.btn-md.btn-primary.btn-block.btn-buynow.button-buynow.cart-103");
    private By cartButton = By.cssSelector("a[aria-controls='cart-total-drawer']");
    private By successToast = By.cssSelector("div.toast.m-3.fade.show");
    private By viewCartButton = By.xpath("//a[contains(text(), 'View Cart')]");
    //locator for the 'Edit Cart' button in the cart drawer
    private By editCartButton = By.xpath("//a[contains(text(), 'Edit cart')]");
    //locator for the header of the shopping cart page
    private By cartPageHeader = By.xpath("//h1[text()='Shopping Cart']");


    public ProductPage() {
        super();
    }
    public Boolean checkAvailability() {
        handleAlerts();
        WebElement element = find(availability);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public String getPrice() {

        handleAlerts();
        WebElement element = find(price);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void addToCart() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(addToCartButton));
        scrollIntoView(element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (checkOverlayPresent()){
            //Handle the overlay if it exists
        }
        if (!isClickSuccessful(element)) {
            clickUsingJavaScript(element);
        }

        waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void clickBuyNow() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(buyNowButton));
        scrollIntoView(element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (checkOverlayPresent()){
            //Handle the overlay if it exists
        }
        if (!isClickSuccessful(element)) {
            clickUsingJavaScript(element);
        }
    }


    private void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    private void clickUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }


    private boolean isClickSuccessful(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkOverlayPresent() {
        By overlayLocator = By.cssSelector("div.mfp-bg.mfp-ready");
        try {
            WebElement overlay = find(overlayLocator);
            return overlay.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clickViewCart(){
        handleAlerts();
        WebElement viewCart = waitForCondition().until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCart.click();
    }

    public void navigateToCart() {
        handleAlerts();
        WebElement cartElement = waitForCondition().until(ExpectedConditions.elementToBeClickable(cartButton));
        cartElement.click();
    }

    public void navigateToEditCartPage() {
        handleAlerts();
        WebElement editButton = waitForCondition().until(ExpectedConditions.elementToBeClickable(editCartButton));
        editButton.click();
        // Verify that we are on the correct shopping cart page.
        verifyShoppingCartPage();

    }
    private void verifyShoppingCartPage() {
        handleAlerts();
        WebElement headerElement = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(cartPageHeader));
        assertTrue("The header of the shopping cart page is not displayed.", headerElement.isDisplayed());
    }
}