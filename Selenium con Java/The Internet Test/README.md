# The Internet Herokuapp Test Exercice

Objective: Learn to work with Selenium with different objects and different types of operations.

Statement: Create in a maven project the different tests taking as a base page: http://the-internet.herokuapp.com/

## Conditioning factors:

Each page must be a page object.

Use "Extent Report" as a reporting mechanism. Visit https://github.com/extent-framework/extentreports-testng-adapter.

All test cases (@Test) must be contained in the same class ("runner").

## To run all test

Open the terminal and run following code

```
mvn clean test

```

### Tests

#### @ Test1:

Access the "Chek boxes" option

Activate Check box 1 (only if not activated already)

Activate Check box 2 (only if not activated already)

#### @ Test2:

Access the option "Dynamic Content"

Verify that 3 profile icons appear

Check that the image /img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg appears

If it does not appear, then the test case is "failed"

If it appears, then the test case is "passed"

#### @ Test3:

Access the "Horizontal Slider" option

Move the slider to position 4.5

#### @ Test4:

Access the option "JavaScript Alerts"

Click on "Click for JS Prompt"

Enter a value in the window's text box and OK

Verify that the entered value appears at the bottom of the buttons

#### @ Test5:

Access the option: "Dynamic Controls"

Select the "Add" option

Select the Check that appears

#### @ Test6:

Access the "Disappearing Elements" option

Click on each of the buttons sequentially

#### @ Test7:

Access the "Challenging DOM" option

If there is at least one "foo" button, click on one of them. Otherwise, click on any of the buttons that contain "baz"

Go through the table that appears on the right side by rows and show the result by console.

#### @ Test8:

Access the "Multiple Windows" option

Check that the message that appears in the new window is: New Window "