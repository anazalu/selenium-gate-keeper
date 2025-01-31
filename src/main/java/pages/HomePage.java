package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signInBtnBy = By.xpath("//a[contains(text(),'Sign in')]");
    private By productListBy = By.xpath("//*[@class='card']");
    private By priceListBy = By.xpath("//span[@data-test='product-price']");


    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnBy));
        signInBtn.click();
    }

    public void clickProduct(int itemWhich) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productListBy));
        List<WebElement> elements = driver.findElements(productListBy);
        if (elements.size() == 0) {
            throw new RuntimeException("No products found."); 
        }
        elements.get(itemWhich).click();
    }

    public String getPrice(int itemWhich) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceListBy));
        List<WebElement> elements = driver.findElements(priceListBy);
        if (elements.size() == 0) {
            throw new RuntimeException("No products found."); 
        }
        return elements.get(itemWhich).getText();
    }

    public ArrayList<String> getPricesList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceListBy));
        List<WebElement> elements = driver.findElements(priceListBy);
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            items.add(elements.get(i).getText());
        }
        return items;
    }
}
