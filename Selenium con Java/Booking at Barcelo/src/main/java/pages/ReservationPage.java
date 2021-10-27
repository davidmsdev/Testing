package pages;

public class ReservationPage extends BasePage {

    private static final String CLOSEPOPUPPATH = "//a[@href='close-modal']";

    public ReservationPage() {
        super(driver);
    }

    public void closePopup() {
        clickElement(CLOSEPOPUPPATH);
    }
}
