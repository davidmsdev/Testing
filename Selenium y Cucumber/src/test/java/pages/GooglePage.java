package pages;

public class GooglePage extends BasePage {

    private String url = "https://www.google.com/";
    private String acceptCookies = "//*[@id='L2AGLb']";
    private String searchButton = "//div[@class='lJ9FBc']//input[@name='btnK']";
    private String searchTextField = "//input[@title='Buscar']";
    
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

    public void enterSearchCriteria(String criteria) {
        write(searchTextField, criteria);
    }

    public String firstResult(String locator) {
        return textFromElement(locator);
    }
}
