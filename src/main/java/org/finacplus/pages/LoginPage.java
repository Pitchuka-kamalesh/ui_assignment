package org.finacplus.pages;


import org.finacplus.utils.LogUtils;
import org.finacplus.utils.TestUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.How.XPATH;

public class LoginPage {
    WebDriver driver;
    TestUtils utils;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.utils = new TestUtils(driver,10);
    }
    @FindBy(how = XPATH,xpath = "//button[@id = 'login']")
    WebElement loginButton;
    @FindBy(how=XPATH,xpath = "//input[@id = 'userName']")
    WebElement usernameTextbox;

    @FindBy(how=XPATH,xpath = "//input[@id = 'password']")
    WebElement passwordTextbox;

    @FindBy(how = XPATH,xpath = "//button[@id = 'newUser']")
    WebElement userButton;



    public void login(String username,String password){
        LogUtils.info("Attempting login with username: : {}", username);
        utils.safeSendKeys(usernameTextbox,username);
        utils.safeSendKeys(passwordTextbox,password);
        passwordTextbox.sendKeys(Keys.ENTER);
        LogUtils.info("Login submitted", "");
    }



}
