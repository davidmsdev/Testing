package pages;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxPage {

    String checkbox1 = "//*[@id='checkboxes']/input[1]";
    String checkbox2 = "//*[@id='checkboxes']/input[2]";
    WebElement elementCheckbox1 = null;
    WebElement elementCheckbox2 = null;
    
    public WebElement getCheckbox1(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            elementCheckbox1 = driver.findElement(By.xpath(checkbox1));
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement checkbox 1 couldn't be found");
            test.fail("The checkbox button number 1 could not be clicked");
            e.printStackTrace();
        }
        
        return elementCheckbox1;
    }

    public WebElement getCheckbox2(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            elementCheckbox2 = driver.findElement(By.xpath(checkbox2));
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement checkbox 2 couldn't be found");
            test.fail("The checkbox button number 2 could not be clicked");
            e.printStackTrace();
        }
        
        return elementCheckbox2;
    }
}
