package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Helpers.Sorting;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signInBtnBy = By.xpath("//a[contains(text(),'Sign in')]");
    private By productListBy = By.xpath("//*[@class='card-footer']");
    private By priceListBy = By.xpath("//span[@data-test='product-price']");
    // private By outListBy = By.xpath("//span[@data-test='out-of-stock']");
    // finds parent of outOfStockSpan: //div[span[@data-test='out-of-stock']]
    //*[@class='card-footer']//span[contains(@data-test, 'out')]
    //*[@class='card-footer']//span[contains(@data-test, 'price')]
    private By sortBy = By.xpath("//form//select");
    private By sortLowToHighBy = By.xpath("//form//select//option[@value='price,asc']");
    private By sortHighToLowBy = By.xpath("//form//select//option[@value='price,desc']");


    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnBy));
        signInBtn.click();
    }

    public String clickAndGetPrice(int itemWhich) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceListBy));
        List<WebElement> prices = driver.findElements(priceListBy);
        List<WebElement> cards = driver.findElements(productListBy);
        if (prices.size() == 0) {
            throw new RuntimeException("No products found."); 
        }
        // if (cards.get(itemWhich).getDomProperty("data-test").contains("out")) {
        //     itemWhich++;
        //     if (itemWhich >= prices.size()) 
        //         throw new RuntimeException("All products out of stock."); 
        // }
        String price = prices.get(itemWhich).getText();
        prices.get(itemWhich).click();
        return price;
    }

    public void sort(Sorting sortHow) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortBy)).click();
        if (sortHow.equals(Sorting.FROMHIGH)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortHighToLowBy)).click();
        } else if (sortHow.equals(Sorting.FROMLOW)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortLowToHighBy)).click();
        } else {
            throw new IllegalArgumentException("No such sorting option.");
        }
        Thread.sleep(1000);
    }

}
