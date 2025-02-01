package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.DriverSetup;

public class AddToCartTest extends DriverSetup {
    public int ITEMONE = 1;

    @Test
    public void testEntireFlow() throws InterruptedException {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Practice Software Testing";
        assertTrue(actualTitle.contains(expectedTitle), "Title mismatch.");
        homePage.sortLowToHigh();
        String homePagePrice = homePage.getPrice(ITEMONE);
        homePage.clickProduct(ITEMONE);
        assertTrue(productDetailPage.isDisplayed(), "PDP failed to display.");
        String pdpPrice = productDetailPage.getPrice();
        System.out.println("Price: " + homePagePrice);
        assertTrue(homePagePrice.equals("$" + pdpPrice), "Price mismatch" + pdpPrice + homePagePrice);
        productDetailPage.clickAddToCartBtn();
        productDetailPage.messageDisplayed();
        productDetailPage.addRelatedItem();
        productDetailPage.messageDisplayed();
        productDetailPage.clickGoToCartBtn();
        assertTrue(checkoutPage.isDisplayed(), "Checkout page not displayed.");
        checkoutPage.clickProceedBtn();
    }
}
