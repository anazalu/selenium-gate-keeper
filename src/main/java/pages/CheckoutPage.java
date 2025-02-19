package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By removeItemBy = By.xpath("//*[@class='btn btn-danger']");
    private By proceedBy = By.xpath("//button[contains(@data-test, 'proceed-1')]");
    private By itemNameListBy = By.xpath("//span[contains(@data-test, 'product-title')]");
    private By quantityListBy = By.xpath("//input[contains(@data-test, 'product-quantity')]"); //TODO: retrieve value
    private By priceListBy = By.xpath("//span[contains(@data-test, 'product-price')]");
    private By subtotalListBy = By.xpath("//span[contains(@data-test, 'line-price')]");
    private By totalPayBy = By.xpath("//td[contains(@data-test, 'cart-total')]");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeItemBy));
        return true;
    }

    public void clickProceedBtn() {
        WebElement proceedBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(proceedBy));
        proceedBtn.click();
    }

    public ArrayList<String> getItemsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNameListBy));
        List<WebElement> elements = driver.findElements(itemNameListBy);
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            items.add(elements.get(i).getText());
        }

        return items;
    }

    public void getQuantities() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityListBy));
        List<WebElement> elements = driver.findElements(quantityListBy);
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(">>>>> Quantities:" + elements.get(i).getDomProperty("value"));
        }
    }

    public void getTotalToPay() {
        String totalToPay = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPayBy)).getText();
        System.out.println(">>>>> Total from Cart page: " + totalToPay);
    }
}
