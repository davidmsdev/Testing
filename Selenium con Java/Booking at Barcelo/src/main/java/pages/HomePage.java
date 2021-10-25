package pages;

public class HomePage extends BasePage {

    public HomePage() {
        super(driver);
    }

    public void navigateToBarceloPage(String url) {
        navigateTo(url);
    }
    
}
