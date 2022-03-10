package pages;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DisappearingElementsPage {

    // XPATH
    String homePath = "//a[contains(text(),'Home')]";
    String aboutPath = "//a[contains(text(),'About')]";
    String contactUsPath = "//a[contains(text(),'Contact Us')]";
    String portfolioPath = "//a[contains(text(),'Portfolio')]";
    String galleryPath = "//a[contains(text(),'Gallery')]";

    // Variables
    boolean existButton = false;
    WebElement home = null;
    WebElement about = null;
    WebElement contactUs = null;
    WebElement portfolio = null;
    WebElement gallery = null;

    public WebElement getHomeLink(WebDriver driver, ExtentTest test) throws InterruptedException {
        try {
            home = driver.findElement(By.xpath(homePath));
            test.pass("Home button clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text 'Home' couldn't found");
            test.fail("The WebElement button with the text 'Home' has not been found");
            e.printStackTrace();
        }
        
        return home;
    }

    public WebElement getAboutLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            about = driver.findElement(By.xpath(aboutPath));
            test.pass("About button clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text 'About' couldn't found");
            test.fail("The WebElement button with the text 'About' has not been found");
            e.printStackTrace();
        }
        
        return about;
    }

    public WebElement getContactLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            contactUs = driver.findElement(By.xpath(contactUsPath));
            test.pass("Contact Us button clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text 'Contact Us' couldn't found");
            test.fail("The WebElement button with the text 'Contact Us' has not been found");
            e.printStackTrace();
        }
        
        return contactUs;
    }

    public WebElement getPortfolioLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            portfolio = driver.findElement(By.xpath(portfolioPath));
            test.pass("Portfolio button clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text 'Portfolio' couldn't found");
            test.fail("The WebElement button with the text 'Portfolio' has not been found");
            e.printStackTrace();
        }
        
        return portfolio;
    }

    public WebElement getGalleryLink(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            gallery = driver.findElement(By.xpath(galleryPath));
            test.pass("Gallery button clicked");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement button with the text 'Gallery' couldn't found");
            test.fail("The WebElement button with the text 'Gallery' has not been found");
            e.printStackTrace();
        }
        
        return gallery;
    }

    public boolean isGalleryButtonVisible(WebDriver driver, ExtentTest test) throws InterruptedException {
        existButton = false;
        List<WebElement> gallery = driver.findElements(By.xpath(galleryPath));

        if (gallery.size() > 0) {
            existButton = true;
        } else {
            existButton = false;
            test.fail("The WebElement button with the text 'Gallery' has not been found");
        }

        return existButton;
    }
    
}
