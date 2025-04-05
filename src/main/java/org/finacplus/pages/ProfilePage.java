package org.finacplus.pages;

import org.finacplus.utils.LogUtils;
import org.finacplus.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.How.XPATH;

public class ProfilePage {
    WebDriver driver;
    TestUtils utils;
    public ProfilePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.utils = new TestUtils(driver,10);
    }
    @FindBy(how = XPATH,xpath = "//button[@id = 'gotoStore']")
    WebElement storeButton;
    @FindBy(how = XPATH,xpath="//label[@id='userName-value']")
    WebElement userName;
    @FindBy(how = XPATH,xpath="//button[@id='submit']")
    WebElement logoutButton;

    public void goToStore(){
        utils.safeClick(storeButton);
    }
    public String getUserName(){
        String name = utils.getText(userName);
        LogUtils.info("Fetched username from profile page: {}", name);
        return name;

    }
    public boolean isLogutButtonPresent(){
        boolean present =  utils.isElementPresent(logoutButton);
        LogUtils.info("Logout button present: {}", present);
        return present;

    }

}
