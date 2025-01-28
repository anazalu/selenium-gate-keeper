package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy
    private By addToCartBtnBy = By.xpath("//button[@id='btn-add-to-cart']");

    @FindBy
    private By goToCartBtnBy = By.xpath("//a[@aria-label='cart']");

    @FindBy
    private By messageBy = By.id("toast-container");

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtnBy));
        return true;
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
        WebElement goToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartBtnBy));
        goToCartBtn.click();
    }
}
