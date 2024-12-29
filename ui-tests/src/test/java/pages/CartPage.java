package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends PageObject {
    private By productName = By.xpath("//form//table/tbody/tr/td[@class='text-left']/a");
    private By productPrice = By.xpath("//form//table/tbody/tr/td[@class='text-right'][2]");

    public String getProductName() {
        WebElement element = find(productName);
        return element.getText();
    }
    public String getProductPrice() {
        WebElement element = find(productPrice);
        return element.getText();
    }


}