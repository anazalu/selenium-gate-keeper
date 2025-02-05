package util;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;

public class DriverSetup {
    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static ProductDetailPage productDetailPage;
    public static CheckoutPage checkoutPage;
    
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // actions = new Actions(driver, Duration.ofSeconds(10));

        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        productDetailPage = new ProductDetailPage(driver, wait);
        checkoutPage = new CheckoutPage(driver, wait);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
