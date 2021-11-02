package exercice2_TheInternetTest;
  
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {

    // XPATHS
    String checkBox = "//a[contains(text(),'Checkbox')]";
    String dynamicContent = "//a[contains(text(),'Dynamic Content')]";
    String horizontalSlider = "//a[contains(text(),'Horizontal Slider')]";
    String javascriptAlert = "//a[contains(text(),'JavaScript Alerts')]";
    String dynamicControls = "//a[contains(text(),'Dynamic Controls')]";
    String disappearingElements = "//a[contains(text(),'Disappearing Elements')]";
    String challengingDOM = "//a[contains(text(),'Challenging DOM')]";
    String multipleWindows = "//a[contains(text(),'Multiple Windows')]";

    // Variables
    WebElement checkboxesLink = null;
    WebElement dynamicContentLink = null;
    WebElement horizontalSliderLink = null;
    WebElement javascriptAlertLink = null;
    WebElement dynamicControlsLink = null;
    WebElement challengingDOMLink = null;
    WebElement multipleWindowsLink = null;
    WebElement disappearingElementsLink = null;

    public void navigateToTheInternetPage(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement getCheckboxesLink(WebDriver driver, ExtentTest test) throws InterruptedException {
        
        try {
            checkboxesLink = driver.findElement(By.xpath(checkBox));
            test.pass("Navigate to Checkboxes page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Checkboxes Link couldn't found");
            test.fail("Navigate to Checkboxes page failed");
            e.printStackTrace();
        }
         
        return checkboxesLink;
    }

    public WebElement getDynamicContentLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            dynamicContentLink = driver.findElement(By.xpath(dynamicContent));
            test.pass("Navigate to Dynamic Content page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Dynamic Content Link couldn't found");
            test.fail("Navigate to Dynamic Content page failed");
            e.printStackTrace();
        }
        
        return dynamicContentLink;
    }

    public WebElement getHorizontalSliderLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            horizontalSliderLink = driver.findElement(By.xpath(horizontalSlider));
            test.pass("Navigate to Horizontal Slider page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Horizontal Slider Link couldn't found");
            test.fail("Navigate to Horizontal Slider page failed");
            e.printStackTrace();
        }
        
        return horizontalSliderLink;
    }

    public WebElement getJavascriptAlertsLink(WebDriver driver, ExtentTest test) throws InterruptedException  {

        try {
            javascriptAlertLink = driver.findElement(By.xpath(javascriptAlert));
            test.pass("Navigate to Javascript Alert page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Javascript Alert Link couldn't found");
            test.fail("Navigate to Javascript Alert page failed");
            e.printStackTrace();
        }
        return javascriptAlertLink;
    }

    public WebElement getDynamicControlsLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            dynamicControlsLink = driver.findElement(By.xpath(dynamicControls));
            test.pass("Navigate to Dynamic Controls page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Dynamic Controls Link couldn't found");
            test.fail("Navigate to Dynamic Controls page failed");
            e.printStackTrace();
        }
        
        return dynamicControlsLink;
    }

    public WebElement getDisappearingElementsLink(WebDriver driver, ExtentTest test) throws InterruptedException {
        
        try {
            disappearingElementsLink = driver.findElement(By.xpath(disappearingElements));
            test.pass("Navigate to Disappearing Elements page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Disappearing Elements Link couldn't found");
            test.fail("Navigate to Disappearing Elements page failed");
            e.printStackTrace();
        }
        
        return disappearingElementsLink;
    }

    public WebElement getChallengingDOMLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            challengingDOMLink = driver.findElement(By.xpath(challengingDOM));
            test.pass("Navigate to Challenging DOM page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Challenging DOM Link couldn't found");
            test.fail("Navigate to Challenging DOM page failed");
            e.printStackTrace();
        }
        
        return challengingDOMLink;
    }

    public WebElement getMultipleWindowsLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            multipleWindowsLink = driver.findElement(By.xpath(multipleWindows));
            test.pass("Navigate to Multiple Windows page succesfully");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement Multiple Windows Link couldn't found");
            test.fail("Navigate to Multiple Windows page failed");
            e.printStackTrace();
        }

        return multipleWindowsLink;
    }
}
