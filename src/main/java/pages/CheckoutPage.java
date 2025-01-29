package pages;

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

    @FindBy
    private By removeItemBy = By.xpath("//*[@class='btn btn-danger']");
    private By proceedBy = By.xpath("//button[contains(@data-test, 'proceed-1')]");

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
}
