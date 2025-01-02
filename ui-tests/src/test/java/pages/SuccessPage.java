package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SuccessPage extends BasePage {

    private By successBreadcrumb = By.xpath("//li[text()='Success']");
    private By successMessage = By.xpath("//p[text()='Your order has been successfully processed!']");
    private By continueButton = By.xpath("//a[text()='Continue']");

    public SuccessPage() {
        super();
    }
    public boolean isSuccessBreadcrumbPresent() {
        handleAlerts();
        WebElement breadcrumb = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(successBreadcrumb));
        return breadcrumb.isDisplayed();
    }
    public boolean issuccessMessagePresent() {
        handleAlerts();
        WebElement header = waitForCondition().until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return header.isDisplayed();
    }

    public void clickContinueButton() {
        handleAlerts();
        WebElement element = waitForCondition().until(ExpectedConditions.elementToBeClickable(continueButton));
        element.click();
    }

}