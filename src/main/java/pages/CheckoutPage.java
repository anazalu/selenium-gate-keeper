package pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By removeItemBy = By.xpath("//*[@class='btn btn-danger']");
    private By proceedBy = By.xpath("//button[contains(@data-test, 'proceed-1')]");
    private By itemNameBy = By.xpath("//span[contains(@data-test, 'product-title')]");

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNameBy));
        List<WebElement> elements = driver.findElements(itemNameBy);
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            items.add(elements.get(i).getText());
        }

        return items;
    }
}
