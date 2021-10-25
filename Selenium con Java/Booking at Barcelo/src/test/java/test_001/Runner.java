package test_001;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.NoSuchElementException;

import pages.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
        // We obtain the date of the current day at 10:00:00 in milliseconds
        LocalDate currentDate = LocalDate.now();
        long currentDateInEpoch = currentDate.toEpochSecond(LocalTime.of(10, 00, 00), ZoneOffset.UTC);

        return new Object[][] {
            {browser, name}, 
        };
    }
    
    @Test (testName = "Booking Barceló", dataProvider = "data-provider")
    public void parameterTest(String browserName, String hotelName) throws InterruptedException {

        ExtentTest test = extent.createTest("Booking in Barceló");

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

        try {
            Assert.assertEquals(homePage.getInputHotelNameValue(), hotelName);
            test.pass("The name of the hotel was entered, it was selected in the dropdown and it appears correctly in the input");
        } catch (AssertionError e) {
            test.fail("The hotel name isn't correct");
        }
        
    }
}