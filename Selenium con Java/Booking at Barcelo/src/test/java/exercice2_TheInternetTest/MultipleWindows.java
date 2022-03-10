package exercice2_TheInternetTest;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultipleWindows {

    // XPATH
    String clickHere = "//a[contains(text(),'Click Here')]";

    // Variables
    String title = "";
    String originalWindow = null;
    WebElement clickHereLink = null;

    public WebElement getClickHereLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            clickHereLink = driver.findElement(By.xpath(clickHere));
            test.pass("Link 'Click here' clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement link with the text 'Click Here' couldn't found");
            test.fail("The WebElement link with the text 'Click Here'  has not been found");
            e.printStackTrace();
        }
        
        return clickHereLink;
    }

    public String getNewWindowTitle(WebDriver driver, WebDriverWait wait) {

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

        // We get the title of the new page
        title = driver.getTitle();
        return title;
    }
}