package pages;

import org.openqa.selenium.WebElement;

public class ReservationPage extends BasePage {

    private static final String CLOSEPOPUPPATH = "//a[@href='close-modal']";
    private static final String ADULTSPATH = "//div[@class='reservation-cart-container_guests']/child::span[1]";
    private static final String CHILDRENSPATH = "//div[@class='reservation-cart-container_guests']/child::span[2]";

    public ReservationPage() {
        super(driver);
    }

    public void closePopup() {
        clickElement(CLOSEPOPUPPATH);
    }

    public String getAdultsGuestText() {
        return getText(ADULTSPATH);
    }

    public String getChildrensGuestText() {
        return getText(CHILDRENSPATH);
    }
}
