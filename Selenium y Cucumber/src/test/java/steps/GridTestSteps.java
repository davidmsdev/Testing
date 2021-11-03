package steps;

import org.junit.Assert;

import cucumber.api.java.en.*;
import pages.GridPage;

public class GridTestSteps {

    GridPage grid = new GridPage();

    @Given("^I navigate to the static table$")
    public void navigateToTheGridPage() {
        grid.navigateToGrid();
    }

    @Then("^I can return the value I wanted$")
    public void returnValue() {
        String value = grid.getValuefromGrid(3,2);

        Assert.assertEquals("r: 2, c: 1", value);
    }  
}
