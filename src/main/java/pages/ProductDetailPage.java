package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Helpers.Quantity;

public class ProductDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartBtnBy = By.xpath("//button[@id='btn-add-to-cart']");
    private By goToCartBtnBy = By.xpath("//a[@aria-label='cart']");
    private By messageBy = By.id("toast-container");
    private By quantityValueBy = By.id("quantity-input");
    private By quantityIncreaseBy = By.id("btn-increase-quantity");
    private By quantityDecreaseBy = By.id("btn-decrease-quantity");
    // TODO: select item No randomly
    private By relatedItemFirstBy = By.xpath("//*[@class='card'][1]");
    private By nameBy = By.xpath("//h1[contains(@data-test, 'product-name')]");
    private By priceBy = By.xpath("//span[@aria-label='unit-price']");

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtnBy));
        return true;
    }

    public String getPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(priceBy)).getText();
    }

    public void clickAddToCartBtn() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtnBy));
        addToCartBtn.click();
    }

    public void messageDisplayed() {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(messageBy));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(messageBy));
    }

    public void clickGoToCartBtn() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(messageBy));
        WebElement goToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartBtnBy));
        goToCartBtn.click();
    }

    public void goToRelatedItem() {
        String currentItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy)).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(relatedItemFirstBy)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(nameBy, currentItemName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy));
        // String nextItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy)).getText();
        // System.out.println(">>>> Moved from: " + currentItemName + " to " + nextItemName);
    }

    public String getItemName() {
        String itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy)).getText();
        System.out.println(">>>> Item name: " + itemName);
        return itemName;
    }

    public String changeAndGetQuantity(Quantity moreOrLess) {
        String initialQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityValueBy)).getDomProperty("value");
        System.out.println(">>>> Initial Quantity: " + initialQuantity);
        if (moreOrLess == Quantity.INCREASE) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(quantityIncreaseBy)).click();
        }
        if (moreOrLess == Quantity.DECREASE) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(quantityDecreaseBy)).click();
        }
        String finalQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityValueBy)).getDomProperty("value");
        // wait.until(ExpectedConditions.refreshed(driver.findElement(quantityValueBy)).getDomProperty("value"));
        System.out.println(">>>> Final Quantity: " + finalQuantity);
        return finalQuantity;
    }
}
