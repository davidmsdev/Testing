package pages;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HorizontalSliderPage {

    // XPATH
    String outputId = "range";
    String slider = "input[type='range']";

    // Variables
    Integer steps = 45;
    String finalSlideValue = "4.5";
    WebElement output = null;
    WebElement sliderInput = null;
    
    public WebElement getOutput(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            output = driver.findElement(By.id(outputId));
            test.pass("The WebElement span with ID 'range' has been found");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement span with ID 'range' couldn't found");
            test.fail("The WebElement span with ID 'range' has not been found");
            e.printStackTrace();
        }
        
        return output;
    }

    public WebElement getSlider(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            sliderInput = driver.findElement(By.cssSelector(slider));
            test.pass("The WebElement input type range has been found");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement input type range couldn't found");
            test.fail("The WebElement input type range has not been found");
            e.printStackTrace();
        }

        return sliderInput;
    }

    public int getSteps() {
        return steps;
    }

    public String getFinalValue() {
        return finalSlideValue;
    }

    public void validateTest(boolean passedTest, ExtentTest test) {

        if(passedTest) {
            test.pass("Test passed succesfully");
        } else {
            test.fail("Test failed");
        }
    }
}
