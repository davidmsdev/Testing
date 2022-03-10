package exercice3_Barcelo;

import java.time.ZonedDateTime;

public class ReservationPage extends BasePage {

    private static final String CLOSEPOPUPPATH = "//a[@href='close-modal']";
    private static final String ADULTSPATH = "//div[@class='reservation-cart-container_guests']/child::span[1]";
    private static final String CHILDRENSPATH = "//div[@class='reservation-cart-container_guests']/child::span[2]";
    private static final String CHECKINPATH = "//div[@class='reservation-cart-container_dates']/child::span[1]";
    private static final String CHECKOUTPATH = "//div[@class='reservation-cart-container_dates']/child::span[2]";


    public ReservationPage() {
        super(driver);
    }

    public void closePopup() {
        clickElement(CLOSEPOPUPPATH);
    }

    public String getTitleText() {
        return getTitle();
    }

    public String getAdultsGuestText() {
        return getText(ADULTSPATH);
    }

    public String getChildrensGuestText() {
        return getText(CHILDRENSPATH);
    }

    public String getCheckinText() {
        return getText(CHECKINPATH);
    }

    public String getCheckoutText() {
        return getText(CHECKOUTPATH);
    }

    public String getFormatedDate(ZonedDateTime date) {
        return formatDate(date);
    }
}
