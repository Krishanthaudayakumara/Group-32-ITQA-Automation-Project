package pages;

import io.github.cdimascio.dotenv.Dotenv;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageObject {

    private String baseUrl;

    public HomePage() {
        Dotenv dotenv = Dotenv.load();
        this.baseUrl = dotenv.get("SERENITY_BASE_URL");
    }


    public void openHomePage() {
        openAt(baseUrl);
    }

    public String selectProductByName(String productName) {
        By productLink = By.xpath("//h4[@class='title']/a[text()='" + productName + "']");
        WebElement element = find(productLink);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void navigateToProductPage(String productName) {
        By productLink = By.xpath("//h4[@class='title']/a[text()='" + productName + "']");
        WebElement element = find(productLink);
        waitForCondition().until(ExpectedConditions.visibilityOf(element));
        element.click();

    }
}