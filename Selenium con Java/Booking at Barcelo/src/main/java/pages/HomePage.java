package pages;

import java.time.ZonedDateTime;

import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // Locators
    String cookiesButton = "//*[@id='main-cookies-dialog']/div/div/div/div[2]/button";
    String hotelNameInputLocator = "//input[@id='destination-fb']";
    String hotelNameDropDownResults = "//div[@class='c-fastbooking__submenu-search-hotels']/ul/li/strong[contains(text(), 'Sants')]";
    String selectGuestButton = "rooms-fb";
    String adultsInputText = "//input[contains(@name,'adults')]";
    String cildrensInputText = "//input[@name='childs']";
    String bookingButtonId = "fastbooking_cta_booking_home";
    String divInterceptedClick = "//div[contains(@class, 'c-loading') and contains(@class,'bhg-loading')";

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

    public long convertCheckinInEpoch(ZonedDateTime date) {
        return convertToEpoch(date);
    }

    public long convertCheckoutInEpoch(ZonedDateTime date) {
        return convertToEpoch(date);
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
        checkinXpath = "//td[@time=" + checkin + " and contains(@class,'datepicker__month-day--visibleMonth')]";
        clickElement(checkinXpath);
    }

    public void clickInCheckoutDate(long checkout) {
        // To skip the "Loading best prices"
        waitInvisibiltyOfElement(divInterceptedClick);
        checkoutXpath = "//td[@time=" + checkout + " and contains(@class,'datepicker__month-day--visibleMonth')]";
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
        findById(bookingButtonId).click();
    }
}
