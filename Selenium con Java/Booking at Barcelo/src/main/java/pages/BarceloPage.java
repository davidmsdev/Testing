package pages;

public class BarceloPage extends BasePage {

    private static final String BOOKINGBUTTON = "//button[@id='fastbooking_cta_booking_hotel']";
    private static final String TITLE = "//title";

    public BarceloPage() {
        super(driver);
    }

    public void clickBookingButton() {
        clickElement(BOOKINGBUTTON);
    }

    public void changeWindow() {
        goToNewWindow();
    }
}
