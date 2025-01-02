package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SuccessPage extends PageObject {

    private By successBreadcrumb = By.xpath("//li[text()='Success']");
    private By successMessage = By.xpath("//p[text()='Your order has been successfully processed!']");
    private By continueButton = By.xpath("//a[text()='Continue']");

    public boolean isSuccessBreadcrumbPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement breadcrumb = wait.until(ExpectedConditions.visibilityOfElementLocated(successBreadcrumb));
            return breadcrumb.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public boolean issuccessMessagePresent() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return header.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clickContinueButton() {
        WebElement element = find(continueButton);
        waitForCondition().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}