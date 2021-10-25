# Booking at Barcelo Test Exercice

Objective: Create a test case on a real website and with the real conditions of a website Conditioners

## Conditioning factors:

 * Using the POM approach 
 * The execution parameters must be parameterized (hotel name, check-in / check-out dates, num Adults and num Children. So that it is possible to change / add the input parameters. Visit the page: https: // www. toolsqa.com/testng/testng-dataproviders/
 * Although the test case will ONLY be executed on Chrome, from the testng.xml file we must pass a parameter with the name of the browser (browser "). In case the Browser parameter does not if "Chrome" the test case will be "failed". 
 * Use Extent Report

## Exercice

 * Access the web: https://www.barcelo.com/es-es/
 * Select the hotel Barcelona Sants
 * Select a dates of stay (it must be after the execution date in the form "Arrival Date 5 days after the Current date and Departure Date 4 days after the arrival date 
 * Select 1 adult and 2 children Click on Book 
 * Verify that the reserved hotel page appears