package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy
    private By signInBtnBy = By.xpath("//a[contains(text(),'Sign in')]");

    @FindBy
    // private By productsListBy = By.xpath("//*[@class='card']");
    private By productOneBy = By.xpath("//*[@class='card'][1]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnBy));
        signInBtn.click();
    }

    public void clickProductOne() {
        // ArrayList<WebElement> productsList = new ArrayList<WebElement>();
        WebElement productOne = wait.until(ExpectedConditions.visibilityOfElementLocated(productOneBy));
        productOne.click();
    }
}
