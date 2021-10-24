package pages;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChallengingDOM {

    // XPATH
    String content= ".large-2.columns";

    // Variables
    String foo = "foo";
    String baz = "baz";
    String buttonFinded = "";
    WebElement linkToClick = null;
    WebElement divContent = null;
    List<WebElement> links = null;
    WebElement table = null;
    List<WebElement> rows = null;

    public WebElement getClickButton(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            divContent = driver.findElement(By.cssSelector(content));
            test.pass("Div with 'content' ID found");
        } catch (NoSuchElementException e) {
            System.out.println("Div with 'content' ID couldn't found");
            test.fail("Div with 'content' ID has not been found");
            e.printStackTrace();
        }
        
        try {
            // Obtenemos una lista con todos los botones
            links = divContent.findElements(By.tagName("a"));
            test.pass("List of buttons found");
        } catch (NoSuchElementException e) {
            System.out.println("List of buttons couldn't found");
            test.fail("List of buttons has not been found");
            e.printStackTrace();
        }
        
        for(WebElement link : links) {
            if (link.getText().contains(foo)) {
                linkToClick = link;
                buttonFinded = foo;
                break;
            } else if (link.getText().contains(baz)) {
                linkToClick = link;
                buttonFinded = baz;
                break;
            } 
        }

        if (buttonFinded == foo) {
            test.pass("foo button clicked");
        }

        if (buttonFinded == baz) {
            test.pass("baz button clicked");
        }

        if (buttonFinded != foo && buttonFinded != baz) {
            test.fail("No buttons with 'foo' or 'baz' found");
        }

        return linkToClick;
    } 

    public void showTableRows(WebDriver driver, ExtentTest test) throws InterruptedException {

        try {
            table = driver.findElement(By.tagName("table"));
            test.pass("Table found");
        } catch (NoSuchElementException e) {
            System.out.println("WebElement table couldn't found");
            test.fail("WebElement table has not been found");
            e.printStackTrace();
        }
        
        try {
            rows = table.findElements(By.tagName("tr"));
            test.pass("List of table rows found");
        } catch (NoSuchElementException e) {
            System.out.println("WList of table rows couldn't found");
            test.fail("List of table rows has not been found");
            e.printStackTrace();
        }
        
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
        test.pass("The tables rows have been printed in conosle");
    }
}
