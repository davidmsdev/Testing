package pages;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utilities {
    
    public static String URL;
    public static String author;
    public static String browser;

    public void getProperties() {

        try {

            Properties pop = new Properties();
            pop.load(new FileReader("src/test/resources/datafiles.properties"));
    
            // We assign the values from the datafiles.properties file
            URL = pop.getProperty("URL");
            author = pop.getProperty("author");
            browser = pop.getProperty("browser");
            
        } catch (IOException e) {
            e.printStackTrace();	
        }
    }
    
    public String getURL() {
        return URL;
    }

    public String getAuthor() {
        return author;
    }

    public String getBrowser() {
        return browser;
    }
}
