package pages;

import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // Locators
    String cookiesButton = "//*[@id='main-cookies-dialog']/div/div/div/div[2]/button";
    String hotelNameInputLocator = "//input[@id='destination-fb']";
    String hotelNameDropDownResults = "//div[@class='c-fastbooking__submenu-search-hotels']/ul/li/strong[contains(text(), 'Sants')]";
    String selectGuestButton = "rooms-fb";
    String adultsInputText = "//input[contains(@name,'adults')]";
    String cildrensInputText = "//input[@name='childs']";
    String bookingButtonXpath = "//button[@id='fastbooking_cta_booking_home']";

    // Variables
    String hotelName = "";
    String checkinXpath = "";
    String checkoutXpath = "";
    WebElement bookingButton = null;

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

    public void clickToSelectGuest() {
        findById(selectGuestButton).click();
    }

    public void enterTheNumberOfAdults(String adults) {
        write(adultsInputText, adults);
    }

    public void enterTheNumberOfChildrens(String childrens) {
        write(cildrensInputText, childrens);
    }

    public void clickBookingButton() {
        clickElement(bookingButtonXpath);
    }
}
