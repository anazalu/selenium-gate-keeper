package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.util.DriverSetup;

public class HomePage {
    protected WebDriver driver;

    private By signInBtnBy = By.cssSelector("a:contains('Sign In')");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new FieldDecorator(driver), this);
    }

    public void clickSignInBtn() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnBy));
        signInBtn.click();
    }    
}
