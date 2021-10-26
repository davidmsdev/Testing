package test_001;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import pages.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Runner {

    // Instances
    Utilities utilities = new Utilities();
    HomePage homePage = new HomePage();

    // Variables
    WebDriver driver = null;
	WebDriverWait wait = null;
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("index.html");

    String url = "";
    String author = "David Morales";
    String correctBrowser = "Chrome";

    @BeforeSuite
    static void setupClass() {
        extent.attachReporter(spark);
    }

    @AfterMethod
    void teardown() {
		extent.flush();  
    }

    @DataProvider (name = "data-provider")
    public Object[][] dpMethod(){

        String browser = "Chrome";
        String name = "Barceló Sants";
        // long oneDayInSeconds = 86400;
        // long oneDayInMiliseconds = oneDayInSeconds * 1000;
        // long add5days = oneDayInMiliseconds * 5;
        // We obtain the date of the current day at 10:00:00 in milliseconds
        ZonedDateTime currentDate = ZonedDateTime.of(LocalDate.now(), LocalTime.of(12, 00, 00), ZoneId.of("Europe/Madrid"));
        long checkin = (currentDate.plus(5, ChronoUnit.DAYS).toEpochSecond())*1000;
        long checkout = (currentDate.plus(9, ChronoUnit.DAYS).toEpochSecond())*1000;

        return new Object[][] {
            {browser, name, checkin, checkout}, 
        };
    }
    
    @Test (testName = "Booking Barceló", dataProvider = "data-provider")
    public void parameterTest(String browserName, String hotelName, long checkin, long checkout) throws InterruptedException {

        ExtentTest test = extent.createTest("Booking in Barceló");

        System.out.println(browserName);
        System.out.println(hotelName);
        System.out.println(checkin);
        //System.out.println(checkout);

        // If it is not the correct browser, the test will stop
        try {
            Assert.assertEquals(correctBrowser, browserName);
            test.pass("Browser test completed successfully");
        } catch (AssertionError e) {
            test.fail("Incorrect browser. Test failed");
            throw e;
        }
        

        // Load properties
        utilities.getProperties();

        url = utilities.getURL();

        homePage.navigateToBarceloPage(url);

        try {
            homePage.clickCookies();
            test.pass("Accept Cookies");
        } catch (NoSuchElementException e) {
            test.fail("Accept button Cookies couldn't found");
        }
        
        homePage.enterHotelName(hotelName);
        homePage.clickHotelNameInDropDownResults();

        // The name of the input has to be the same as the name we write
        try {
            Assert.assertEquals(homePage.getInputHotelNameValue(), hotelName);
            test.pass("The name of the hotel was entered, it was selected in the dropdown and it appears correctly in the input");
        } catch (AssertionError e) {
            test.fail("The hotel name isn't correct");
        }

        try {
            homePage.clickInCheckinDate(checkin);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            homePage.clickInCheckinDate(checkin);
        }
        
        homePage.clickInCheckoutDate(checkout);
        
    }
}