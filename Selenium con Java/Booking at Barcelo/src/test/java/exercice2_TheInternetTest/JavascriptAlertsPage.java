package exercice2_TheInternetTest;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptAlertsPage {
    
    // XPATH
    String pormpt = "//button[contains(text(),'Click for JS Prompt')]";
    String results = "result";

    // Variables
    String keys = "18";
    WebElement promptButton = null;
    WebElement result = null;

    public WebElement getPromptButton(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            promptButton = driver.findElement(By.xpath(pormpt));
            test.pass("The WebElement button with the text Click for JS Prompt has been found");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text Click for JS Prompt couldn't found");
            test.fail("The WebElement button with the text Click for JS Prompt has not been found");
            e.printStackTrace();
        }
        
        return promptButton;
    }

    public WebElement getResult(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            result = driver.findElement(By.id(results));
            
            test.pass("The WebElement with the ID 'result' has been found");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement with the ID 'result' couldn't found");
            test.fail("The WebElement with the ID 'result' has not been found");
            e.printStackTrace();
        }
        
        return result;
    }

    public String getKeysTosend() {
        return keys;
    }
}
