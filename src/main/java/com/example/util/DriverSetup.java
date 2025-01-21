package com.example.util;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.pages.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static HomePage homePage;
        
        @BeforeAll
        public static void setUp() {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://magento.softwaretestingboard.com/");
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
            homePage = new HomePage(driver, wait);
        }

        @AfterAll
        public static void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }    
}
