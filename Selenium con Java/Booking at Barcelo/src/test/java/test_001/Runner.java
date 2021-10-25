package test_001;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import pages.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    ExtentTest testBuild = null;

    @BeforeSuite
    static void setupClass() {
        
    }

    @DataProvider (name = "data-provider")
    public Object[][] dpMethod(){

        String name = "Sants";
        // We obtain the date of the current day at 10:00:00 in milliseconds
        LocalDate currentDate = LocalDate.now();
        long currentDateInEpoch = currentDate.toEpochSecond(LocalTime.of(10, 00, 00), ZoneOffset.UTC);

        System.out.println(currentDateInEpoch);
        return new Object[][] {
            { "First-Value", 2 , "tercero" }, 
        };
    }
    
    @Test (dataProvider = "data-provider")
    public void parameterTest(String val1, int val2, String val3) throws InterruptedException {

        // Load properties
        utilities.getProperties();

        url = utilities.getURL();

        homePage.navigateToBarceloPage(url);

    }
	
}