package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    // Locators
    String cookiesButton = "//*[@id='main-cookies-dialog']/div/div/div/div[2]/button";
    String hotelNameInputLocator = "//input[@id='destination-fb']";
    String hotelNameDropDownResults = "//div[@class='c-fastbooking__submenu-search-hotels']/ul/li/strong[contains(text(), 'Sants')]";

    // Variables
    String hotelName = "";
    String checkinXpath = "";
    String checkoutXpath = "";

    public HomePage() {
        super(driver);
    }

    public void navigateToBarceloPage(String url) {
        navigateTo(url);
    }

    public void clickCookies() {
        clickElement(cookiesButton);
    }

    public void enterHotelName(String name) {
        hotelName = name;
        write(hotelNameInputLocator, hotelName);
    }

    public String getInputHotelNameValue() {
        return find(hotelNameInputLocator).getAttribute("value");
    }

    public void clickHotelNameInDropDownResults() {
        clickElement(hotelNameDropDownResults);
    }

    public void clickInCheckinDate(long checkin) {
        checkinXpath = "//td[@time=" + checkin + "]";
        clickElement(checkinXpath);
    }

    public void clickInCheckoutDate(long checkout) {
        checkoutXpath = "//*[@id='month-2-1']/tbody/tr[1]/td[@time=" + checkout +"]";
        clickElement(checkoutXpath);
    }
}
