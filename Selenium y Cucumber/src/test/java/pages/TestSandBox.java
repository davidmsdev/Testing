package pages;

public class TestSandBox extends BasePage {
    
    private String categoryDropdown = "//select[@id='dropdown']";

    public TestSandBox() {
        super(driver);
    }

    public void navigateToSandbox() {
        navigateTo("http://the-internet.herokuapp.com/dropdown");
    }

    public void selectCategory(String category) {
        selectFromDropdownByValue(categoryDropdown, category);
    }
}
