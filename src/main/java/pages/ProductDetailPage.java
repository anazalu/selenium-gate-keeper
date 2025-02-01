package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartBtnBy = By.xpath("//button[@id='btn-add-to-cart']");
    private By goToCartBtnBy = By.xpath("//a[@aria-label='cart']");
    private By messageBy = By.id("toast-container");
    // todo - select item No randomly
    private By relatedItemOneBy = By.xpath("//*[@class='card'][1]");
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

    public boolean messageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageBy));
        return true;
    }

    public void clickGoToCartBtn() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(messageBy));
        WebElement goToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartBtnBy));
        goToCartBtn.click();
    }

    public void addRelatedItem() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(relatedItemOneBy)).click();
    }

    public String getItemName() {
        String itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy)).getText();
        return itemName;
    }
}
