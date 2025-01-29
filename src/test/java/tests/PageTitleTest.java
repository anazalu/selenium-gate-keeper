package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.DriverSetup;

public class PageTitleTest extends DriverSetup {

    @Test
    public void testTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Practice Software Testing";
        assertTrue(actualTitle.contains(expectedTitle), "Title mismatch.");
        homePage.clickProductOne();
        assertTrue(productDetailPage.isDisplayed(), "PDP failed to display.");
        productDetailPage.clickAddToCartBtn();
        productDetailPage.messageDisplayed();
        productDetailPage.clickGoToCartBtn();
        assertTrue(checkoutPage.isDisplayed(), "Checkout page not displayed.");
        checkoutPage.clickProceedBtn();
    }
}
