package steps;

import java.util.List;

import cucumber.api.java.en.*;
import pages.ListPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListSteps {

    private static Logger LOGGER = LoggerFactory.getLogger(ListSteps.class);

    ListPage list = new ListPage();
    
    @Given("^I navigate to the list page$")
    public void navigateListPage() {
        list.navigateToListPage();
    }

    @When("^I search the list$")
    public void searchTheList() throws InterruptedException{
        list.enterSearchCriteria();
    }

    @Then("^I can find the text in the list$")
    public void theTableIsThere() {
        List<String> lista = list.getAllSearchResults();
        boolean textIsThere = lista.contains("Burien, Washington");

        if(textIsThere) {
            System.out.println("PASSED");
            LOGGER.info("This is a logger message");
        } else {
            throw new Error("FAILED");
        }
    }
}
