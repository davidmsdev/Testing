package pages;

public class GooglePage extends BasePage {

    private String url = "https://www.google.com/";
    private String acceptCookies = "//*[@id='L2AGLb']";
    private String searchButton = "//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[3]/center[1]/input[1]";
    
    public GooglePage() {
        super(driver);
    }

    public void navigateToGoogle() {
        navigateTo(url);
    }

    public void acceptCookies() {
        clickElement(acceptCookies);
    }

    public void clickGoogleSearch() {
        clickElement(searchButton);
    }
}
