package exercice2_TheInternetTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utilities {
    
    public static String URL;

    public void getProperties() {

        try {

            Properties pop = new Properties();
            pop.load(new FileReader("src/test/resources/datafiles.properties"));
    
            // We assign the values from the datafiles.properties file
            URL = pop.getProperty("urlTheInternet");
            
        } catch (IOException e) {
            e.printStackTrace();	
        }
    }
    
    public String getURL() {
        return URL;
    }
}
