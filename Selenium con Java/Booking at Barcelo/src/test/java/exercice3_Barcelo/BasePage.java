package exercice3_Barcelo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    protected static String originalWindow = "";
    protected static DateTimeFormatter formatter = null;
    protected static String formattedString = "";

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

    public String getText(String locator) {
        return find(locator).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void goToNewWindow() {
        // Save the actual window
        originalWindow = driver.getWindowHandle();

        // We wait until the following window opens
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // We go through the two windows and if we are in the one that is not original it is the new window
        for (String windowhandle : driver.getWindowHandles()) {
			if(!originalWindow.contentEquals(windowhandle)) {
				driver.switchTo().window(windowhandle);
				break;
			}
		}
    }

    public boolean waitInvisibiltyOfElement(String locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    public long convertToEpoch(ZonedDateTime date) {
        return date.toEpochSecond()*1000;
    }

    public String formatDate(ZonedDateTime date) {
        formatter = DateTimeFormatter.ofPattern("E, d MMM YYYY");
        return formattedString = date.format(formatter).replace(".","");
    }
}
