package exercice2_TheInternetTest;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicContentPage {

    boolean threeIcons = false;
    boolean existImage = false;
    String src = "";
    String imageToSearch = "/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg";
    Integer numberOfIcons = 3;
    WebElement content;
    List <WebElement> rows = null;

    public WebElement getContent(WebDriver driver, ExtentTest test) throws InterruptedException {
        
        try {
            content = driver.findElement(By.id("content"));
            test.pass("The WebElement containing the 3 images has been found");
        } catch (NoSuchElementException e) {
            System.out.println("The WebElement div with 'content' ID couldn't be found");
            test.fail("The WebElement containing the 3 images has not been found");
            e.printStackTrace();
        }
        
        return content;
    }
    
    public boolean thereAreThreeIconsInTheProfiles(WebDriver driver, ExtentTest test) throws InterruptedException {

        getContent(driver, test);

        try {
            // A partir del elemento con ID content obtenemos una lista de las etiquetas img dentro de este
            rows = content.findElements(By.tagName("img"));
            test.pass("'img' WebElements found in content");
        } catch (NoSuchElementException e) {
            System.out.println("Could not find WebElements 'img' in the content");
            test.fail("Could not find WebElements 'img' in the content");
            e.printStackTrace();
        }
        

        // Comprobamos si hay 3 elementos img
        if (numberOfIcons == rows.size()) {
            threeIcons = true;
            test.pass("3 images found");
        }

        return threeIcons;
    }

    public boolean theImageExists(WebDriver driver, ExtentTest test) throws InterruptedException {

        getContent(driver, test);

        rows = content.findElements(By.tagName("img"));

        for (WebElement row : rows) {
            src = row.getAttribute("src");       
            if (src.contains(imageToSearch)) {
                existImage = true;
            } else {
                existImage = false;
            }
        }

        if (existImage) {
            test.pass("The image with src " + imageToSearch + " has been found");
            test.pass("Dynamic Content Test completed succesfully");
        } else {
            test.fail("The image with src " + imageToSearch + " has not been found");
            test.fail("Dynamic Content Test failed");
        }

        return existImage;
    }
}
