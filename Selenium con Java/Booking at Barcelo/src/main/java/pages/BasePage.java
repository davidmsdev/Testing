package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    // Static initializer block
    static {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 10);
    }

    // Constructor
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    // Methods
    public static void navigateTo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement find(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public WebElement findById(String locator) {
        return driver.findElement(By.id(locator));
    }

    public void clickElement(String locator) {
        find(locator).click();
    }

    public void write(String locator, String text) {
        find(locator).sendKeys(Keys.CONTROL + "a");
        find(locator).sendKeys(Keys.DELETE);
        find(locator).sendKeys(text);
    }
}
