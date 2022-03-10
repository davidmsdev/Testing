package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebElement;

public class ListPage extends BasePage {

    private static Logger LOGGER = LoggerFactory.getLogger(ListPage.class);

    private String url = "https://andreidbr.github.io/JS30/06AjaxTypeAhead/index.html";
    private String searchField = "//body/form[1]/input[1]";
    private String searchResults = "name";
    
    public ListPage() {
        super(driver);
    }

    public void navigateToListPage() {
        navigateTo(url);
    }

    public void enterSearchCriteria(String state) throws InterruptedException {
        
        Thread.sleep(600);
        write(searchField, state);
        
    }

    public List<String> getAllSearchResults() {
        List<WebElement> list = bringMeAllElements(searchResults);
        List<String> stringsFromList = new ArrayList<String>();
        for(WebElement e : list) {
            stringsFromList.add(e.getText());
        }
        return stringsFromList;
    }
}
