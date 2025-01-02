package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage extends PageObject {

    public BasePage() {
    }

    public void handleAlerts() {
        if(isAlertPresent()){
            handleAlert();
        }
    }

    private boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    private void handleAlert() {
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            //If no alert is present, carry on
            System.out.println("No alert was present");
        }
    }

    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }
}