package runner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.InvalidArgumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import exercice3_Barcelo.*;

public class BarceloTest {

    // Instances
    Utilities utilities = new Utilities();
    HomePage homePage = new HomePage();
    BarceloPage barceloPage = new BarceloPage();
    ReservationPage reservationPage = new ReservationPage();

    // Variables
    WebDriver driver = null;
	WebDriverWait wait = null;
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("index.html");

    String url = "";
    String author = "";
    String browserTest = "";
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
        ZonedDateTime currentDate = ZonedDateTime.of(LocalDate.now(), LocalTime.of(12, 00, 00), ZoneId.of("Europe/Madrid"));
        ZonedDateTime checkin = currentDate.plus(5, ChronoUnit.DAYS);
        ZonedDateTime checkout = currentDate.plus(9, ChronoUnit.DAYS);
        String adults = "2";
        String childrens = "1";

        return new Object[][] {
            {browser, name, checkin, checkout, adults, childrens}, 
        };
    }

    @Parameters ({"browser"})
    @Test (testName = "Booking Barceló", dataProvider = "data-provider")
    public void parameterTest(String browser, String hotelName, ZonedDateTime checkin, ZonedDateTime checkout, String adults, String childrens) throws InterruptedException {

        // Load properties
        utilities.getProperties();

        url = utilities.getURL();
        author = utilities.getAuthor();
        browserTest = utilities.getBrowser();

        long checkinEpoch = homePage.convertCheckinInEpoch(checkin);
        long checkoutEpoch = homePage.convertCheckoutInEpoch(checkout);

        ExtentTest test = extent.createTest("Booking in Barceló").assignAuthor(author).assignDevice(browserTest);

        // If it is not the correct browser, the test will stop
        try {
            Assert.assertEquals(correctBrowser, browser);
            test.pass("Browser test completed successfully");
        } catch (AssertionError e) {
            test.fail("Incorrect browser. Test failed");
            throw e;
        }
        
        try {
            homePage.navigateToBarceloPage(url);
            test.pass("Navigate to " + url);
        } catch (InvalidArgumentException e) {
            test.fail("The url could not be accessed");
        }
        
        try {
            homePage.clickCookies();
            test.pass("Accept Cookies");
        } catch (NoSuchElementException e) {
            test.fail("Accept button Cookies couldn't found");
        }
        
        try {
            homePage.enterHotelName(hotelName);
            homePage.clickHotelNameInDropDownResults();
            test.pass("Entered the hotel name: " + hotelName + " and selected in dropdown results");
        } catch (NoSuchElementException e) {
            test.fail("Data could not be entered, input or dropdown could not be found");
        }
        
        // The name of the input has to be the same as the name we write
        try {
            Assert.assertEquals(homePage.getInputHotelNameValue(), hotelName);
            test.pass("The name of the hotel was entered, it was selected in the dropdown and it appears correctly in the input");
        } catch (AssertionError e) {
            test.fail("The hotel name isn't correct");
        }

        try {
            homePage.clickInCheckinDate(checkinEpoch);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            homePage.clickInCheckinDate(checkinEpoch);
        }
        
        homePage.clickInCheckoutDate(checkoutEpoch);
        homePage.clickToSelectGuest();
        homePage.enterTheNumberOfAdults(adults);
        homePage.enterTheNumberOfChildrens(childrens);

        try {
            homePage.clickBookingButton();
            test.pass("Clicked booking button on homepage");
        } catch (ElementClickInterceptedException e) {
            test.fail("Couldn't click on the booking button on homepage");
        }
        
        try {
            barceloPage.clickButton();
            test.pass("The booking button on the hotel intermediate page has been clicked");
        } catch (NoSuchElementException e) {
            test.fail("The button could not be clicked on the hotel intermediate page");
        }
        
        barceloPage.changeWindow();

        System.out.println(reservationPage.getTitleText());
        try {
            Assert.assertTrue(reservationPage.getTitleText().contains(hotelName));
            test.pass("We are in the Barcelo Sants Hotel page");
        } catch (AssertionError e) {
            test.fail("We aren't in the Barcelo Sants Hotel page");
        }

        try {
            reservationPage.closePopup();
            test.pass("Popup closed");
        } catch (NoSuchElementException e) {
            test.fail("Popup couldn't found");
        }
        
        test.info("Adults expected: " + adults);
        try {
            Assert.assertTrue(reservationPage.getAdultsGuestText().contains(adults));
            test.pass("Adults results: " + reservationPage.getAdultsGuestText());
        } catch (AssertionError e) {
            test.fail("The expected result and the obtained result are not the same");
        }

        test.info("Childrens expected: " + childrens);
        try {
            Assert.assertTrue(reservationPage.getChildrensGuestText().contains(childrens));
            test.pass("Childrens results: " + reservationPage.getChildrensGuestText());
        } catch (AssertionError e) {
            test.fail("The expected result and the obtained result are not the same");
        }

        test.info("Checkin expected -> " + reservationPage.formatDate(checkin));
        try {
            test.info("Checkin result: " + reservationPage.getCheckinText());
            Assert.assertTrue(reservationPage.getCheckinText().contains(reservationPage.formatDate(checkin)));
            test.pass("Checkin result:" + reservationPage.getCheckinText());
        } catch (AssertionError e) {
            test.fail("The expected result and the obtained result are not the same");
        }

        test.info("Checkout expected -> " + reservationPage.formatDate(checkin));
        try {
            test.info("Checkout result: " + reservationPage.getCheckoutText());
            Assert.assertTrue(reservationPage.getCheckoutText().contains(reservationPage.formatDate(checkout)));
            test.pass("Checkout result:" + reservationPage.getCheckoutText());
        } catch (AssertionError e) {
            test.fail("The expected result and the obtained result are not the same");
        }
    }
}