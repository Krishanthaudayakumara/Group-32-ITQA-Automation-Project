package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryPage extends PageObject {

    public void openHomepage() {
        openUrl("https://ecommerce-playground.lambdatest.io/");
    }

    public boolean isCategoryVisible(String category) {
        WebElementFacade categoryLink = $(By.linkText(category));
        categoryLink.waitUntilVisible();
        return categoryLink.isVisible();
    }

    public void clickCategory(String category) {
        WebElementFacade categoryLink = $(By.linkText(category));
        categoryLink.click();
    }

    public boolean isCategoryTitleVisible(String categoryTitle) {
        WebElementFacade title = $(By.xpath("//h1[text()='" + categoryTitle + "']"));
        return title.isVisible();
    }

    public boolean isBreadcrumbVisible() {
        WebElementFacade breadcrumbTrail = $(By.xpath("//*[@id=\"entry_212388\"]/nav/ol/li[2]"));
        return breadcrumbTrail.isVisible();
    }

    public String getBreadcrumbText() {
        WebElementFacade breadcrumbTrail = $(By.xpath("//*[@id=\"entry_212388\"]/nav/ol/li[2]"));
        return breadcrumbTrail.getText();
    }

    public boolean isPageContentVisible() {
        WebElementFacade pageContent = $(By.xpath("//*[@id=\"entry_212391\"]"));
        return pageContent.isDisplayed();
    }

    public boolean areAllProductsDisplayingInfo() {
        List<WebElementFacade> products = $$(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'product-layout')]"));
        for (WebElementFacade product : products) {
            if (!product.find(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'product-thumb-top')]/div[contains(@class, 'image')]")).isVisible()
                    || !product.find(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'caption')]/h4")).isVisible()
                    || !product.find(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'caption')]/div[contains(@class, 'price')]")).isVisible()) {
                return false;
            }
        }
        return true;
    }


    public boolean doAllProductsHaveButtons(String addToCart, String addToWishlist, String compare) {
        List<WebElementFacade> products = $$(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'product-layout')]"));
        for (WebElementFacade product : products) {
            if (!isButtonVisible(product, addToCart)
                    || !isButtonVisible(product, addToWishlist)
                    || !isButtonVisible(product, compare)) {
                return false;
            }
        }
        return true;
    }

    private boolean isButtonVisible(WebElementFacade product, String buttonTitle) {
        return product.find(By.xpath(".//div[contains(@class, 'product-action')]//button[contains(@title, '" + buttonTitle + "')]")).isVisible();
    }

    public boolean isProductCountMatching() {
        WebElementFacade countElement = $(By.xpath("//*[@id=\"input-limit-212402\"]/option[1]"));

        if (!countElement.isVisible()) {
            return false;
        }
        int countText = Integer.parseInt(countElement.getText());
        int totalProductsDisplayed = $$(By.xpath("//div[contains(@class, 'product-layout')]")).size();
        return totalProductsDisplayed == countText;
    }

    public void clickShopByCategory() {
        WebElementFacade category = $(By.xpath("//*[@id='entry_217832']/a"));
        category.click();
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        WebElement minPriceField = find(By.xpath("//*[@id=\"mz-filter-panel-0-0\"]/div/div[2]/input[1]"));
        minPriceField.clear();  // Clear any existing value
        minPriceField.sendKeys(minPrice);  // Set the minimum price

        WebElement maxPriceField = find(By.xpath("//*[@id=\"mz-filter-panel-0-0\"]/div/div[2]/input[2]"));
        maxPriceField.clear();
        maxPriceField.sendKeys(maxPrice);

    }

    public boolean areProductsUnderPrice(String price) {
        // Find all product price elements on the page using $$() which returns a list of WebElementFacade objects
        List<WebElementFacade> productPrices = $$(By.xpath("//*[@id='entry_212408']//div[contains(@class, 'caption')]/div[contains(@class, 'price')]"));
        // Parse the filter price (remove "$" and any commas)
        double filterPrice = Double.parseDouble(price.replace("$", "").replace(",", "").trim());

        // Iterate over each WebElementFacade in the list
        for (WebElementFacade productPrice : productPrices) {
            // Extract and parse the price of each product (remove "$" and commas)
            String priceText = productPrice.getText().replace("$", "").replace(",", "").trim();
            double productPriceValue = Double.parseDouble(priceText);

            if (productPriceValue > filterPrice) {
                return false; // Found a product that exceeds the filter price
            }
        }
        return true; // All products are under the specified price
    }



    public boolean areNoProductsAbovePrice(String price) {
        // Find all product price elements on the page using $$() which returns a list of WebElementFacade objects
        List<WebElementFacade> productPrices = $$(By.xpath("//p[@class='price']"));
        double filterPrice = Double.parseDouble(price.replace("$", "").replace(",", "").trim());

        for (WebElementFacade productPrice : productPrices) {
            String priceText = productPrice.getText().replace("$", "").replace(",", "").trim();
            double productPriceValue = Double.parseDouble(priceText);

            // If any product price exceeds the filter price, return false
            if (productPriceValue > filterPrice) {
                return false;
            }
        }
        return true; // All products are below or equal to the specified price
    }

    public void clickNextPage() {
        WebElementFacade nextButton = $(By.xpath("//*[@id=\"entry_212409\"]/div/div[1]/ul/li[6]"));
        scrollTo(nextButton);
        nextButton.click();
    }

    public void clickPreviousPage() {
        WebElementFacade previousButton = $(By.xpath("//*[@id=\"entry_212409\"]/div/div[1]/ul/li[2]"));
        scrollTo(previousButton);
        previousButton.click();
    }

    private void scrollTo(WebElementFacade element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element); // Scrolls the element into view
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public boolean areProductsDisplayed() {
        List<WebElementFacade> products = $$(By.xpath("//div[contains(@class, 'product-layout')]"));
        return !products.isEmpty();
    }

    public void addProductToWishList() {
        // Locate the product image (or any other element you want to hover over)
        WebElementFacade product = $(By.xpath("//*[@id=\"mz-product-grid-image-28-212408\"]/div/div[1]"));

        // Perform hover action using Actions class
        Actions actions = new Actions(getDriver());
        actions.moveToElement(product).perform();  // Hover over the product element

        // Locate the "Wish List" button and click it
        WebElementFacade wishListButton = $(By.xpath("//*[@id=\"entry_212408\"]/div/div[1]/div/div[1]/div[2]/button[2]"));
        wishListButton.click();
    }

    public boolean isWishListUpdated() {
        WebElementFacade wishListToast = $(By.xpath("//*[@id=\"notification-box-top\"]/div/div[2]/div/p"));
        String wishListSuccess = wishListToast.getText();
        System.out.println("Notification text: " + wishListSuccess);
        return wishListSuccess.contains("Success:");
    }
}
