package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import util.DriverSetup;

public class AddToCartTest extends DriverSetup {

    @Test
    public void testEntireFlow() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Practice Software Testing";
        assertTrue(actualTitle.contains(expectedTitle), "Title mismatch.");
        homePage.clickProductOne();
        assertTrue(productDetailPage.isDisplayed(), "PDP failed to display.");
        String expectedItemOneName = productDetailPage.getItemName();
        productDetailPage.clickAddToCartBtn();
        productDetailPage.messageDisplayed();
        productDetailPage.addRelatedItem();
        productDetailPage.messageDisplayed();
        productDetailPage.clickGoToCartBtn();
        assertTrue(checkoutPage.isDisplayed(), "Checkout page not displayed.");
        ArrayList<String> actualItems = checkoutPage.getItemsList();
        // WIP
        // assertTrue(actualItems.contains(expectedItemOneName), "Item" + expectedItemOneName + "not found in the cart.");
        checkoutPage.clickProceedBtn();
    }
}
