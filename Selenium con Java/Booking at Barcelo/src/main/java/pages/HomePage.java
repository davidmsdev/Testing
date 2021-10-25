package pages;

public class HomePage extends BasePage {

    // Locators
    String cookiesButton = "//*[@id='main-cookies-dialog']/div/div/div/div[2]/button";
    String hotelNameInputLocator = "//input[@id='destination-fb']";
    String hotelNameDropDownResults = "//div[@class='c-fastbooking__submenu-search-hotels']/ul/li/strong[contains(text(), 'Sants')]";

    // Variables
    String hotelName = "";

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
    
}
