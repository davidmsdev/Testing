package alten_001;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ChallengingDOM;
import pages.CheckboxPage;
import pages.DisappearingElementsPage;
import pages.DynamicContentPage;
import pages.DynamicControlsPage;
import pages.HomePage;
import pages.HorizontalSliderPage;
import pages.JavascriptAlertsPage;
import pages.MultipleWindows;
import pages.Utilities;

public class Runner {

	// Instances
	HomePage homePage = new HomePage();
	CheckboxPage checkboxes = new CheckboxPage();
	HorizontalSliderPage horizontalSlider = new HorizontalSliderPage();
	DynamicContentPage dynamicContent = new DynamicContentPage();
	JavascriptAlertsPage javascriptAlert = new JavascriptAlertsPage();
	DynamicControlsPage dynamicControl = new DynamicControlsPage();
	DisappearingElementsPage disappearingElements = new DisappearingElementsPage();
	ChallengingDOM challengingDOM = new ChallengingDOM();
	MultipleWindows multipleWindows = new MultipleWindows();
	Utilities utilities = new Utilities();

	// Variables
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
	WebDriver driver = null;
	WebDriverWait wait = null;
	String url = "";
	String author = "David Morales";
	String browser = "Chrome";
	ExtentTest testBuild = null;
	Integer slideSteps = horizontalSlider.getSteps();
	String finalValue = horizontalSlider.getFinalValue();
	String slideOutput = "";
	String keysToSend = "";
	String aboutUrl = "about";
	String contactUrl = "contact-us";
	String portfolioUrl = "portfolio";
	String galleryUrl = "gallery";
	String newWindowText = "New Window";

	public ExtentTest createNewTest(String testName, String author, String browser) {
		return testBuild = extent.createTest(testName).assignAuthor(author).assignDevice(browser);
	}

	
	@BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("The Internet Reports");
		extent.attachReporter(spark);
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);

		// Load properties
		utilities.getProperties();	

		// Variables
		url = utilities.getURL();

		homePage.navigateToTheInternetPage(url, driver);
    }

    @AfterEach
    void teardown() {
		extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }

	/**
	 * Access the "Chek boxes" option
	 * Activate Check box 1 (only if not activated already)
	 * Activate Check box 2 (only if not activated already)
	 * 
	 * @throws InterruptedException
	 */    
    @Test
	void checkboxes() throws InterruptedException {

		boolean isCheckBox1Activated = false;
		boolean isCheckBox2Activated = false;

		ExtentTest test = createNewTest("Checkboxes Test", author, browser);
		
		homePage.getCheckboxesLink(driver, test).click();

		isCheckBox1Activated = checkboxes.getCheckbox1(driver, test).isSelected();
		
		if(!isCheckBox1Activated) {
			checkboxes.getCheckbox1(driver, test).click();	
			test.pass("Checkbox 1 is selected");
		}

		isCheckBox2Activated = checkboxes.getCheckbox2(driver, test).isSelected();

		if(!isCheckBox2Activated) {
			checkboxes.getCheckbox2(driver, test).click();	
		}

		if (isCheckBox2Activated) {
			test.pass("Checkbox 2 is selected");
		}

		try {
			assertTrue(checkboxes.getCheckbox1(driver, test).isSelected());
			assertTrue(checkboxes.getCheckbox2(driver, test).isSelected());
			test.pass("Checkboxes Test completed succesfully");
		} catch(AssertionFailedError e) {
			test.fail("Hheckboxes Test failed");
		}
	}

	/**
	 * AccessAccess the "Dynamic Content" option
	 * Verify that 3 profile icons appear
	 * Check that the image /img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg appears
	 * If it does not appear, then the test case is "failed"
	 * If it appears, then the test case is "passed"
	 * 
	 * @throws InterruptedException
	 */

	@Test
	void dynamicContent() throws InterruptedException {

		ExtentTest test2 = createNewTest("Dynamic Content Test", author, browser);

		homePage.getDynamicContentLink(driver, test2).click();

		assertTrue(dynamicContent.thereAreThreeIconsInTheProfiles(driver, test2));
		assertTrue(dynamicContent.theImageExists(driver, test2));
	}


	/**
	 * Access the "Horizontal Slider" option
	 * Move the slider to position 4.5
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void horizontalSlider() throws InterruptedException {

		ExtentTest test3 = createNewTest("Horizontal Slider Test", author, browser);

		homePage.getHorizontalSliderLink(driver, test3).click();

		Actions action = new Actions(driver);
		action.dragAndDropBy(horizontalSlider.getSlider(driver, test3), slideSteps, 0).perform();
		slideOutput = horizontalSlider.getOutput(driver, test3).getText();
		test3.info("Slider value expected: " + horizontalSlider.getFinalValue());
		test3.info("Currently Slider value: " + slideOutput);

		try {
			assertEquals(finalValue, slideOutput);
			test3.pass("Horizontal Slider Test completed succesfully");
		} catch(AssertionFailedError e) {
			test3.fail("Horizontal Slider Test failed");
		}
	}


	/**
	 * Access the option "JavaScript Alerts"
	 * Click on "Click for JS Prompt"
	 * Enter a value in the window's text box and OK
	 * Verify that the entered value appears at the bottom of the buttons
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void javascriptAlert() throws InterruptedException {

		keysToSend = javascriptAlert.getKeysTosend();

		ExtentTest test4 = createNewTest("Javascript Alert Test", author, browser);

		homePage.getJavascriptAlertsLink(driver, test4).click();
		javascriptAlert.getPromptButton(driver, test4).click();

		Alert alert = driver.switchTo().alert();
		alert.sendKeys(keysToSend);
		alert.accept();

		test4.info("Keys sended: " + keysToSend);
		test4.info("Result: " + javascriptAlert.getResult(driver, test4).getText());
		assertTrue(javascriptAlert.getResult(driver, test4).getText().contains(keysToSend));

		try {
			assertTrue(javascriptAlert.getResult(driver, test4).getText().contains(keysToSend));
			test4.pass("Javascript Alert Test completed succesfully");
		} catch(AssertionFailedError e) {
			test4.fail("Javascript Alert Test failed");
		}
	}

	/**
	 * Access the option: "Dynamic Controls"
	 * Select the "Add" option
	 * Select the Check that appears
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void dynamicControls() throws InterruptedException {

		ExtentTest test5 = createNewTest("Dynamic Controls Test", author, browser);

		homePage.getDynamicControlsLink(driver, test5).click();

		dynamicControl.getRemoveButton(driver, test5).click();
		dynamicControl.getAddButton(wait, test5).click();
		dynamicControl.getCheckboxInput(wait, test5).click();

		try {
			assertTrue(dynamicControl.getCheckboxInput(wait, test5).isSelected());
			test5.pass("Checkbox checked");
			test5.pass("Dynamic Controls Test completed succesfully");
		} catch(AssertionFailedError e) {
			test5.fail("Dynamic Controls Test failed");
		}
	}

	/**
	 * Access the "Disappearing Elements" option
	 * Click on each of the buttons sequentially
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void disappearingElements() throws InterruptedException {

		ExtentTest test6 = createNewTest("Dissappering Eelements Test", author, browser);

		homePage.getDisappearingElementsLink(driver, test6).click();

		disappearingElements.getHomeLink(driver, test6).click();
		assertTrue(driver.getCurrentUrl().contains(utilities.getURL()));
		homePage.getDisappearingElementsLink(driver, test6).click();

		disappearingElements.getAboutLink(driver, test6).click();
		assertTrue(driver.getCurrentUrl().contains(aboutUrl));
		driver.navigate().back();

		disappearingElements.getContactLink(driver, test6).click();
		assertTrue(driver.getCurrentUrl().contains(contactUrl));
		driver.navigate().back();

		disappearingElements.getPortfolioLink(driver, test6).click();
		assertTrue(driver.getCurrentUrl().contains(portfolioUrl));
		driver.navigate().back();

		if (disappearingElements.isGalleryButtonVisible(driver, test6)) {
			disappearingElements.getGalleryLink(driver, test6).click();
			assertTrue(driver.getCurrentUrl().contains(galleryUrl));
		}	
	}

	/**
	 * Access the "Challenging DOM" option
	 * If there is at least one "foo" button, click on one of them. Otherwise, click on any of the buttons that contain "baz"
	 * Go through the table that appears on the right side by rows and show the result by console.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void challengingDOM() throws InterruptedException {

		ExtentTest test7 = createNewTest("Challenging DOM Test", author, browser);

		homePage.getChallengingDOMLink(driver, test7).click();
		challengingDOM.getClickButton(driver, test7).click();	
		challengingDOM.showTableRows(driver, test7);
		test7.pass("Challenging DOM Test completed succesfully");
	}

	/**
	 * Access the "Multiple Windows" option
	 * Check that the message that appears in the new window is: New Window "
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void multipleWindows() throws InterruptedException {

		ExtentTest test8 = createNewTest("Multiple Windows Test", author, browser);

		homePage.getMultipleWindowsLink(driver, test8).click();

		multipleWindows.getClickHereLink(driver, test8).click();

		String text = multipleWindows.getNewWindowTitle(driver, wait);

		try {
			test8.info("Expected text: " + newWindowText);
			test8.info("Obtained text: " + text);
			assertEquals(newWindowText, text);
			test8.pass("Multiple Windows Test completed succesfully");
		} catch(AssertionFailedError e) {
			test8.fail("Multiple Windows Test failed");
		}
	}
}