package exercice2_TheInternetTest;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicControlsPage {

    // XPATH
    String removeButton = "#checkbox-example button";
    String addButton = "//button[contains(text(),'Add')]";
    String checkboxId = "checkbox";

    // Variables
    WebElement remove = null;
    WebElement add = null;
    WebElement checkbox = null;

    public WebElement getRemoveButton(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            remove = driver.findElement(By.cssSelector(removeButton));
            test.pass("Click on the remove button");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement remove button couldn't be found");
            test.fail("The WebElement remove button has not been found");
            e.printStackTrace();
        }
        
        return remove;
    }

    public WebElement getAddButton(WebDriverWait wait, ExtentTest test) throws InterruptedException {

        try {
            add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addButton)));
            test.pass("Click on the Add button");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement add button couldn't be found");
            test.fail("The WebElement add button has not been found");
            e.printStackTrace();
        }
        
        return add;
    }

    public WebElement getCheckboxInput(WebDriverWait wait, ExtentTest test) throws InterruptedException {

        try {
            checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(checkboxId)));
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement checkbox couldn't be found");
            test.fail("The WebElement checkbox has not been found");
            e.printStackTrace();
        }
        
        return checkbox;
    }  
}
