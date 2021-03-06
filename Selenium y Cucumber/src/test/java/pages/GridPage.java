package pages;

public class GridPage extends BasePage {

    private String url = "https://1v2njkypo4.csb.app/";
    private String cell = "//*[@id='root']/div/";
    private String mainTable = "//*[@id='root']/div/table";
    
    public GridPage() {
        super(driver); 
    }

    public void navigateToGrid() {
        navigateTo(url);
    }

    public String getValuefromGrid(int row, int column) {
        return getValueFromTable(cell, row, column);
    }

    public boolean cellStatus() {
        return elementIsDisplayed(mainTable);
    }
}
