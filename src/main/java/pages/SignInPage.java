package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy
    // private By registerBtnBy = By.cssSelector("a.action.create.primary");
    private By registerBtnBy = By.xpath("//a[@aria-label='Register your account']");

    public SignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerBtnBy));
        return true;
    }

    public void clickRegisterBtn() {
        WebElement registerBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(registerBtnBy));
        registerBtn.click();
    }    
}
