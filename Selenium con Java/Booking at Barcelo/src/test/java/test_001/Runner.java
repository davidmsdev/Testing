package test_001;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;


public class Runner {

    @Parameters({"myName"})
    @Test
    public void parameterTest(String myName) {
        System.out.println("Valor del parametro: " + myName);
    }
	
}