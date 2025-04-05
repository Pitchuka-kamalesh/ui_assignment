package org.finacplus.testcases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.finacplus.pages.BooksPage;
import org.finacplus.pages.LoginPage;
import org.finacplus.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoQaTestCases {
    private static final Logger log = LogManager.getLogger(DemoQaTestCases.class);
    WebDriver driver;
    LoginPage loginPage;
    ProfilePage profilePage;
    BooksPage booksPage;

    @BeforeClass
    public void setUp(){
        log.info("=== BeforeClass: setUp ===");
        driver = new FirefoxDriver();
        driver.get("https://demoqa.com/login");

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        booksPage = new BooksPage(driver);
        log.info("Initialized all page objects and navigated to login page.");
    }


    @Test
    public void uiAssignment(){
        log.info("=== Test: uiAssignment ===");

        loginPage.login("test@test", "Test@Test1");
        log.info("Login completed.");

        Assert.assertEquals(profilePage.getUserName(),"test@test","User Name didn't match");
        log.info("Username validated successfully.");

        Assert.assertTrue(profilePage.isLogutButtonPresent());
        log.info("Logout button is present.");

        profilePage.goToStore();
        log.info("Navigated to Book Store.");

        booksPage.searchBook("Learning JavaScript Design Patterns");
        log.info("Book searched successfully.");

        Assert.assertEquals(booksPage.getBookTitle(),"Learning JavaScript Design Patterns","Books title didn't match");
        log.info("Book title validated.");

        booksPage.getBookDetails();
        log.info("Fetched book details.");

        booksPage.logOut();
        log.info("Logged out successfully.");

    }

    @AfterClass
    public void tearDown(){
        log.info("=== AfterClass: tearDown ===");
        driver.quit();
        log.info("Browser closed.");
    }

}
