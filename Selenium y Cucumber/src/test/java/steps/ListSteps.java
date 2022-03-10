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

    @When("^I search (.+) in the list$")
    public void searchTheList(String state) throws InterruptedException{
        list.enterSearchCriteria(state);
    }

    @Then("^I can find (.+) in the list$")
    public void theTableIsThere(String city) {
        List<String> lista = list.getAllSearchResults();
        boolean textIsThere = lista.contains(city);

        if(textIsThere) {
            System.out.println("PASSED");
            LOGGER.info("This is a logger message");
        } else {
            throw new Error("FAILED");
        }
    }
}
