package org.finacplus.pages;

import org.finacplus.utils.FileUtils;
import org.finacplus.utils.LogUtils;
import org.finacplus.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.How.XPATH;

public class BooksPage {
    WebDriver driver;
    TestUtils utils;

    public BooksPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.utils = new TestUtils(driver, 10);
        LogUtils.info("BooksPage initialized","");
    }

    @FindBy(how = XPATH, xpath = "//input[@id='searchBox']")
    WebElement searchBox;

    @FindBy(how = XPATH, xpath = "//label[@id='userName-value']")
    WebElement userName;

    @FindBy(how = XPATH, xpath = "//button[@id='submit']")
    WebElement logoutButton;

    @FindBy(how = XPATH, xpath = "//button[@id='gotoStore']")
    WebElement bookStorebutton;

    @FindBy(how = XPATH, xpath = "//span[starts-with(@id,'see-book')]/ancestor::div[@class='rt-tr -odd']")
    List<WebElement> searchResults;

    @FindBy(how = XPATH, xpath = "//span[starts-with(@id,'see-book')]")
    WebElement bookTitle;



    public void searchBook(String value){
        LogUtils.info("Searching for book: {}", value);
        utils.safeSendKeys(searchBox, value);
    }

    public void getBookDetails(){
        LogUtils.info("Extracting search results and writing to output.txt","");
        for (WebElement ele : searchResults){
            String bookInfo = ele.getText();
            LogUtils.info("Book found: {}", bookInfo);
            FileUtils.writeToFile("output.txt", bookInfo);
        }
    }

    public String getBookTitle(){
        String title = utils.getText(bookTitle);
        LogUtils.info("Book title from search result: {}", title);
        return title;
    }

    public void logOut(){
        LogUtils.info("Clicking Logout button on BooksPage","");
        utils.safeClick(logoutButton);
    }


}
