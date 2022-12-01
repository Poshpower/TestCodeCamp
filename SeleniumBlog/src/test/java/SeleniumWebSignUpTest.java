
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumWebSignUpTest {

    private WebDriver driver;

    @BeforeTest
    public void setUpWebPage() {
        //locate where the chrome is and set up
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        //Open the chromedriver
        driver = new ChromeDriver();
        //Input the page URL you want to test
        driver.get("https://selenium-blog.herokuapp.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Maximize the window
        driver.manage().window().maximize();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println(driver.getCurrentUrl());
        String expectedUrl = "https://selenium-blog.herokuapp.com";

        //Test1. Verify user input the correct url and his on the right page
        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Task 1:The user is on the right page ");
        } else {
            System.out.println("Task 1: The user is on the wrong page");
        }
    }

    @Test(priority = 1)
    public void signUpPage() {
        // Click on the SignUp button
        driver.findElement(By.xpath("//div[2]/div/a[2]")).click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Test2. Verify that when user clicks on the signup button, the user is directed to the signup page
        String expectedURL = "https://selenium-blog.herokuapp.com/signup";
        String actualURL = driver.getCurrentUrl();
        if (expectedURL.contains(actualURL)) {
            System.out.println("Task 2:This is the correct signup webpage");
        } else {
            System.out.println("Task 2: This is the wrong sign up webpage");
        }
    }

    @Test(priority = 2)
    public void negativeSignupUsernameLessThan3() {

        String signUpPage = "https://selenium-blog.herokuapp.com/signup";

        if (driver.getCurrentUrl().equals(signUpPage)) {
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        } else {
            driver.navigate().to(signUpPage);
        }
        //CliK and type in the username in the username field
        driver.findElement(By.id("user_username")).sendKeys("To");
        //CliK and type in the Email in the Email  field
        driver.findElement(By.id("user_email")).sendKeys("Toname@mailinator.com");
        //CliK and type in the password in the password field
        driver.findElement(By.id("user_password")).sendKeys("password");
        // Click on the SignUp button
        driver.findElement(By.id("submit")).click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Test3. Verify that user cannot sign up with username Less than 3 characters
        WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
        String actualErrorMessage = actualError.getText();
        // Verifying if an error message is displayed
        if (actualError.isDisplayed()) {
            System.out.println(" Task 3:The sign up failed");
        } else {
            System.out.println(" Task 3:The sign up is successful");
        }
        // Verifying the error message text
        String expectedErrorMessage = "Username is too short (minimum is 3 characters)";
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println(" Task 3:" + actualErrorMessage);
        } else {
            System.out.println("Task 3:The error message is not for Username input");
        }
    }


    @Test(priority = 3)
    public void negativeSignupInvalidEmail() {
        //Test4. Verify that user cannot sign up with invalid email address

        String signUpPage = "https://selenium-blog.herokuapp.com/signup";

        if (driver.getCurrentUrl().equals(signUpPage)) {
            driver.navigate().refresh();
        } else {
            driver.navigate().to(signUpPage);
        }

        //Refresh the page to reset all input fields
        driver.navigate().refresh();
        //CliK and type in the username in the username field
        driver.findElement(By.id("user_username")).sendKeys("Toll");
        //CliK and type in the Email in the Email  field
        driver.findElement(By.id("user_email")).sendKeys("Tollmailinator.com");
        //CliK and type in the password in the password field
        driver.findElement(By.id("user_password")).sendKeys("password");
        // Click on the SignUp button
        driver.findElement(By.id("submit")).click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Test4. Verify that user cannot sign up with invalid email address

        // Verifying if an error message is displayed
        WebElement emailValidation = driver.findElement(By.id("user_email"));
        String actualValidationErrorMessage = emailValidation.getAttribute("validationMessage");
        if (emailValidation.isDisplayed()) {
            System.out.println(" Task 4:The sign up failed");
        } else {
            System.out.println(" Task 4:The sign up is successful");
        }
        // Verifying the error message text
        String expectedValidationErrorMessage = "Please include an '@' in the email address. 'Tollmailinator.com' is missing an '@'.";
        if (expectedValidationErrorMessage.equals(actualValidationErrorMessage)) {
            System.out.println(" Task 4:" + actualValidationErrorMessage);
        } else {
            System.out.println(" Task 4:The error message is not for email input");
        }
    }


    @Test(priority = 4)
    public void negativeSignupPasswordLessThanOrEqual1() {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";

        if (driver.getCurrentUrl().equals(signUpPage)) {
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        } else {
            driver.navigate().to(signUpPage);
        }
        //Refresh the page to reset all input fields
        driver.navigate().refresh();
        //CliK and type in the username in the username field
        driver.findElement(By.id("user_username")).sendKeys("Tosine");
        //CliK and type in the Invalid Email in the Email  field
        driver.findElement(By.id("user_email")).sendKeys("Tosin@mailinator.com");
        //CliK and type in the password in the password field
        driver.findElement(By.id("user_password")).sendKeys("");
        // Click on the SignUp button
        driver.findElement(By.id("submit")).click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Test5. Verify user cannot log in with password less than or equal to one

        // Verifying if an error message is displayed
        WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
        String actualErrorMessage = actualError.getText();
        String expectedErrorMessage = "Password can't be blank";
        if (actualError.isDisplayed()) {
            System.out.println(" Task 5:The sign up failed");
        } else {
            System.out.println(" Task 5:The sign up is successful");
        }

        // Verifying the error message text
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println(" Task 5:" + actualErrorMessage);
        } else {
            System.out.println("Task 5:The error message is not for Password input");
        }
    }


    @Test(priority = 5)
    public void negativeSignupWithEmptyField() {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";

        if (driver.getCurrentUrl().equals(signUpPage)) {
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        } else {
            driver.navigate().to(signUpPage);
        }
        //Refresh the page to reset all input fields
        driver.navigate().refresh();
        //CliK and type in the username in the username field
        driver.findElement(By.id("user_username")).sendKeys("Tiwa");
        //CliK and clear the content of the field
        driver.findElement(By.id("user_email")).sendKeys("");
        //CliK and type in the Email in the Email  field
        driver.findElement(By.id("user_password")).sendKeys("");
        // Click on the SignUp button
        driver.findElement(By.id("submit")).click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Test6. Verify user cannot sign up with either/all the fields bLank

        // Verifying if an error message is displayed
        WebElement actualError = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div"));
        String actualErrorMessage = actualError.getText();
        String expectedErrorMessage = "Email can't be blank\n" +
                "Email is invalid\n" +
                "Password can't be blank";
        if (actualError.isDisplayed()) {
            System.out.println(" Task 6:The sign up failed");
        } else {
            System.out.println(" Task 6:The sign up is successful");
        }
        // Verifying the error message text
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println(" Task 6:" + actualErrorMessage);
        } else {
            System.out.println("Task 6:The error message is not for empty input");
        }

    }


    @Test(priority = 6)
    public void positiveSignup() {
        //Task 7. Verify that user is successfully signed up when valid details are inputted.

        String signUpPage = "https://selenium-blog.herokuapp.com/signup";

        if (driver.getCurrentUrl().equals(signUpPage)) {
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        } else {
            driver.navigate().to(signUpPage);
        }

        //Refresh the page to reset all input fields
        driver.navigate().refresh();
        //CliK and type in the username in the username field
        driver.findElement(By.id("user_username")).sendKeys("Folukemi");
        //CliK and clear the content of the field
        driver.findElement(By.id("user_email")).clear();
        //CliK and type in the Email in the Email  field
        driver.findElement(By.id("user_email")).sendKeys("Folukemi@mailinator.com");

        //CliK and type in the password in the password field
        driver.findElement(By.id("user_password")).sendKeys("password");

        // Click on the SignUp button
        driver.findElement(By.id("submit")).click();

        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Task 7. Verify that User1 item is present on the item List page.
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://selenium-blog.herokuapp.com/users";
        if (expectedURL.equals(actualURL)) {
            System.out.println(" Task 7: Sign up was successful");
        } else {
            System.out.println(" Task 7:The Signup failed");
        }

    }

    @Test(priority = 7)
    public void locateUser1() {
        // Click on the User1 item on the list
        WebElement user1Item = driver.findElement(By.xpath("//div[2]/div[1]/ul/div/div/li[1]/a"));

        //Task 8. Verify that User1 item is present on the item List page.
        String actualUser1Item = user1Item.getText();
        String expectedUser1Item = "user1";
        if (expectedUser1Item.equals(actualUser1Item)) {
            System.out.println(" Task 8: User 1 is on the list");
        } else {
            System.out.println(" Task 8: User 1 is not on th list");
        }

        // click the user1 Item
        user1Item.click();
        //make the page wait for few minute for the page to fully load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test(priority = 7)
    public void searchItem() {

        //Search for an Item and confirm the item is on the list
        WebElement searchedItem = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div[1]/a"));

        //Task 9.Verify that the item searched for on the User1 page is present.
        String expectedItemName = "Learn XPath";
        String actualItemName = searchedItem.getText();
        if (expectedItemName.equals(actualItemName)) {
            System.out.println(" Task 9: Item searched for is present");
        } else {
            System.out.println(" Task 9: Item searched for is not present");
        }
        //Click on the search Item to view its content
        searchedItem.click();

    }

    @Test(priority = 8)
    public void logout() {

        //CliK on the logout button to log out
        driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[3]/a")).click();

        //Task 10. I Verify that when the user logout, he/she is directed back to the home page
        WebElement logoutMessageDisplay = driver.findElement(By.id("flash_success"));
        String expectedLogOutMessage = "You have logged out";
        String actualLogOutMessage = logoutMessageDisplay.getText();

        //Verify the logout was successful
        if (logoutMessageDisplay.isDisplayed()) {
            System.out.println(" Task 10:The logout is successful");
        } else {
            System.out.println(" Task 6:The sign up is successful");
        }
        // Verifying the success message
        if (expectedLogOutMessage.equals(actualLogOutMessage)) {
            System.out.println(" Task 10:" + actualLogOutMessage);
        } else {
            System.out.println("Task 10:The log out was not successful");
        }

        // Verify the logout returns to the homepage
        String expectedURL = "https://selenium-blog.herokuapp.com/";
        String actualURL = driver.getCurrentUrl();
        // Verifying the success message
        if (expectedURL.equals(actualURL)) {
            System.out.println(" Task 10 we are back on :" + actualURL);
        } else {
            System.out.println("Task 10:The log out was not successful");
        }

    }

    @AfterTest
    public void after() {
        //  Quit the driver
        driver.close();
    }

}
