package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.DriverSetup;
import util.Helpers.Filtering;
import util.Helpers.Quantity;
import util.Helpers.Sorting;

public class AddToCartTest extends DriverSetup {
    public final int FIRST_ITEM = 0;
    public final int LOW_PRICE = 20;
    public final int HIGH_PRICE = 150;

    @Test
    public void testEntireFlow() throws InterruptedException {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Practice Software Testing";
        assertTrue(actualTitle.contains(expectedTitle), "Title mismatch.");
        homePage.setSlider(LOW_PRICE, HIGH_PRICE);
        homePage.filterFor(Filtering.HAND_TOOLS);
        homePage.sortItems(Sorting.FROMLOW);
        // TODO: assert prices are sorted

        String homePagePrice = homePage.clickAndGetPrice(FIRST_ITEM);

        assertTrue(productDetailPage.isDisplayed(), "PDP failed to display.");
        String pdpPrice = productDetailPage.getPrice();
        System.out.println(">>>>> Price from PD Page: " + pdpPrice);
        assertEquals(homePagePrice, "$" + pdpPrice, "Price mismatch " + pdpPrice + " " + homePagePrice);
        // first item
        productDetailPage.getItemName();
        productDetailPage.clickAddToCartBtn();
        productDetailPage.goToRelatedItem();
        // second item
        productDetailPage.getItemName();
        productDetailPage.changeAndGetQuantity(Quantity.INCREASE);
        productDetailPage.clickAddToCartBtn();
        productDetailPage.goToRelatedItem();
        // third item
        productDetailPage.getItemName();
        productDetailPage.changeAndGetQuantity(Quantity.DECREASE);
        productDetailPage.clickAddToCartBtn();
        productDetailPage.messageDisplayed();

        productDetailPage.clickGoToCartBtn();
        assertTrue(checkoutPage.isDisplayed(), "Checkout page not displayed.");
        
        checkoutPage.getQuantities();
        checkoutPage.getTotalToPay();
        checkoutPage.getItemsList();
        checkoutPage.clickProceedBtn();
    }
}
