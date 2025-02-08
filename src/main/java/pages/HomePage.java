package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Helpers.Filtering;
import util.Helpers.Sorting;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By signInBtnBy = By.xpath("//a[contains(text(),'Sign in')]");
    private By priceListBy = By.xpath("//span[@data-test='product-price']");
    private By sortBy = By.xpath("//form//select");
    private By sortLowToHighBy = By.xpath("//form//select//option[@value='price,asc']");
    private By sortHighToLowBy = By.xpath("//form//select//option[@value='price,desc']");
    private By sliderCeilBy = By.className("ngx-slider-ceil");
    private By minHandleBy = By.className("ngx-slider-pointer-min");
    private By maxHandleBy = By.className("ngx-slider-pointer-max");
    private By minValueNowBy = By.className("ngx-slider-model-value");
    private By maxValueNowBy = By.className("ngx-slider-model-high");
    private By sliderSizeBy = By.className("ngx-slider");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn() {
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnBy));
        signInBtn.click();
    }

    public void getSliderWidth() {
        WebElement sliderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderSizeBy));
        int sliderWidth = sliderElement.getSize().getWidth();
        System.out.println(">>>>> Width: " + sliderWidth);
    }

    public void setSlider(int priceMin, int PriceMax) throws InterruptedException {
        WebElement sliderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderSizeBy));
        int sliderWidth = sliderElement.getSize().getWidth();
        System.out.println(">>>>> Width: " + sliderWidth);
        WebElement minHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(minHandleBy));
        WebElement maxHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(maxHandleBy));
        WebElement minValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(minValueNowBy));
        WebElement maxValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(maxValueNowBy));
        String sliderCeil = driver.findElement(sliderCeilBy).getText();
        System.out.println(">>>>> Max limit: " + sliderCeil);
        String minValue = minValueNow.getText();
        String maxValue = maxValueNow.getText();
        System.out.println(">>>>> Min Value: " + minValue);
        System.out.println(">>>>> Max Value: " + maxValue);
        float coeff = Integer.valueOf(sliderCeil)/Integer.valueOf(sliderWidth);
        actions.clickAndHold(minHandle).moveByOffset(10, 0).release().perform();;
        actions.clickAndHold(maxHandle).moveByOffset(100, 0).release().perform();;
        Thread.sleep(1000);
        // wait.until(ExpectedConditions.textToBePresentInElement(minValueNow, String.valueOf(PriceMax)));
        minValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(minValueNowBy));
        maxValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(maxValueNowBy));
        minValue = minValueNow.getText();
        maxValue = maxValueNow.getText();
        System.out.println(">>>>> Min Value: " + minValue);
        System.out.println(">>>>> Max Value: " + maxValue);
    }

    public String clickAndGetPrice(int itemWhich) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceListBy));
        List<WebElement> prices = driver.findElements(priceListBy);
        if (prices.size() == 0) {
            throw new RuntimeException("No products found."); 
        }
        Boolean itemAvailable = false;
        while (itemAvailable == false) {
            String numStr = String.valueOf(itemWhich + 1);
            By outOfStockBy = By.xpath("//a[@class='card'][" + numStr + "]//span[contains(@data-test, 'out')]");
            if (driver.findElements(outOfStockBy).size() > 0) {
                itemWhich++;
                System.out.println(">>>>> Skip to next item.");
            } else {
                itemAvailable = true;
            }
            if (itemWhich + 1 >= prices.size()) {
                throw new RuntimeException("All relevant products are out of stock."); 
            }
        }
        String price = prices.get(itemWhich).getText();
        System.out.println(">>>>> Price from Homepage " + price);
        prices.get(itemWhich).click();
        return price;
    }

    public void sortItems(Sorting sortEnum) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortBy)).click();
        if (sortEnum.equals(Sorting.FROMHIGH)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortHighToLowBy)).click();
        } else if (sortEnum.equals(Sorting.FROMLOW)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortLowToHighBy)).click();
        } else {
            throw new IllegalArgumentException("No such sorting option.");
        }
        // TODO: wait until element change
        Thread.sleep(1000);
    }

    public void filterFor(Filtering filterEnum) throws InterruptedException {
        String filterStr = "";
        switch (filterEnum) {
            case PLIERS:
                filterStr = "Pliers";
                break;
            case CHISELS:
                filterStr = "Chisels";
                break;
            case HAND_TOOLS:
                filterStr = "Hand Tools";
                break;
            default:
                break;
        }
        By filterForBy = By.xpath("//label[contains(text(), '" + filterStr + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterForBy)).click();
        Thread.sleep(1000);
    }
}
