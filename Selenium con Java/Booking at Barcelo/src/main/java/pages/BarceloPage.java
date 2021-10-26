package pages;

public class BarceloPage extends BasePage {

    String bookingButton = "//button[@id='fastbooking_cta_booking_hotel']";

    public BarceloPage() {
        super(driver);
    }

    public void clickBookingButton() {
        clickElement(bookingButton);
    }

    public void changeWindow() {
        goToNewWindow();
    }
}
