package com.example.tests.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class CategoryPage extends PageObject {

    public void openHomepage() {
        openUrl("https://ecommerce-playground.lambdatest.io/");
    }

    public boolean isCategoryVisible(String category) {
        WebElementFacade categoryLink = $(By.linkText(category));
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
        WebElementFacade breadcrumbTrail = $(By.cssSelector(".breadcrumb"));
        return breadcrumbTrail.isVisible();
    }

    public String getBreadcrumbText() {
        WebElementFacade breadcrumbTrail = $(By.cssSelector(".breadcrumb"));
        return breadcrumbTrail.getText();
    }

    public boolean isPageContentVisible() {
        WebElementFacade pageContent = $(By.id("content"));
        return pageContent.isDisplayed();
    }

    public boolean areAllProductsDisplayingInfo() {
        List<WebElementFacade> products = $$(".product-layout");
        for (WebElementFacade product : products) {
            if (!product.find(By.cssSelector(".product-title")).isVisible()
                    || !product.find(By.cssSelector(".img-fluid")).isVisible()
                    || !product.find(By.cssSelector(".price")).isVisible()) {
                return false;
            }
        }
        return true;
    }

    public boolean doAllProductsHaveButtons(String addToCart, String addToWishlist, String compare) {
        List<WebElementFacade> products = $$(".product-layout");
        for (WebElementFacade product : products) {
            if (!product.find(By.xpath("//button[text()='" + addToCart + "']")).isVisible()
                    || !product.find(By.xpath("//button[text()='" + addToWishlist + "']")).isVisible()
                    || !product.find(By.xpath("//button[text()='" + compare + "']")).isVisible()) {
                return false;
            }
        }
        return true;
    }

    public boolean isProductCountMatching() {
        WebElementFacade productCountElement = $(By.xpath("//div[@class='product-count']"));
        if (!productCountElement.isVisible()) {
            return false;
        }

        String countText = productCountElement.getText();
        int totalProductsDisplayed = $$(".product-layout").size();
        int totalProductsMentioned = Integer.parseInt(countText.split(" ")[0]);

        return totalProductsDisplayed == totalProductsMentioned;
    }
}
