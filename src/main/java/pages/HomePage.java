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
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private final By signInBtnBy = By.xpath("//a[contains(text(),'Sign in')]");
    private final By priceListBy = By.xpath("//span[@data-test='product-price']");
    private final By sortBy = By.xpath("//form//select");
    private final By sortLowToHighBy = By.xpath("//form//select//option[@value='price,asc']");
    private final By sortHighToLowBy = By.xpath("//form//select//option[@value='price,desc']");
    private final By sliderCeilBy = By.className("ngx-slider-ceil");
    private final By minHandleBy = By.className("ngx-slider-pointer-min");
    private final By maxHandleBy = By.className("ngx-slider-pointer-max");
    private final By minValueNowBy = By.className("ngx-slider-model-value");
    private final By maxValueNowBy = By.className("ngx-slider-model-high");
    private final By sliderRangeBy = By.className("ngx-slider");
    private final By sliderCircleBy = By.xpath("//span[@role='slider'][1]");

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

    public void setSlider(int priceMin, int priceMax) throws InterruptedException {
        WebElement rangeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderRangeBy));
        WebElement circleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderCircleBy));
        int circleWidthPx = circleElement.getSize().getWidth();
        int sliderWidthPx = rangeElement.getSize().getWidth() - circleWidthPx;
        System.out.println(">>>>> Width: " + sliderWidthPx);

        WebElement minHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(minHandleBy));
        WebElement maxHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(maxHandleBy));
        WebElement minValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(minValueNowBy));
        WebElement maxValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(maxValueNowBy));
        String sliderCeil = driver.findElement(sliderCeilBy).getText();
        System.out.println(">>>>> Max limit: " + sliderCeil);

        String minValue = minValueNow.getText();
        String maxValue = maxValueNow.getText();
        System.out.println(">>>>> Min default: " + minValue);
        System.out.println(">>>>> Max default: " + maxValue);

        double maxValueNum = Double.parseDouble(sliderCeil);
        double coeff = (double) sliderWidthPx / maxValueNum;
        System.out.println(">>>>> Coeff: " + coeff);
        // 50 px = 36 $ (1.38888888889)

        int minOffsetUSD = - Integer.valueOf(minValue) + priceMin;
        int maxOffsetUSD = - Integer.valueOf(maxValue) + priceMax;
        System.out.println(">>>>> Min diff USD: " + minOffsetUSD);
        System.out.println(">>>>> Max diff USD: " + maxOffsetUSD);
        
        double minOffsetPx = (double) minOffsetUSD * coeff;
        // double minOffsetPx = (double) minOffsetUSD * coeff + circleWidthPx / 2;
        double maxOffsetPx = (double) maxOffsetUSD * coeff;
        System.out.println(">>>>> minOffset: " + minOffsetPx);
        System.out.println(">>>>> maxOffset: " + maxOffsetPx);

        actions.clickAndHold(maxHandle).moveByOffset((int)maxOffsetPx, 0).release().perform();        
        // Thread.sleep(1000);
        actions.clickAndHold(minHandle).moveByOffset((int)minOffsetPx, 0).release().perform();
        Thread.sleep(1000);
        // wait.until(ExpectedConditions.textToBePresentInElement(minValueNow, String.valueOf(PriceMax)));
        minValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(minValueNowBy));
        maxValueNow = wait.until(ExpectedConditions.visibilityOfElementLocated(maxValueNowBy));
        minValue = minValueNow.getText();
        maxValue = maxValueNow.getText();
        System.out.println(">>>>> Min after moving: " + minValue);
        System.out.println(">>>>> Max after moving: " + maxValue);
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
    