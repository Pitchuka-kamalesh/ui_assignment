package org.finacplus.pages;

import org.finacplus.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.How.XPATH;

public class HomePage {
    WebDriver driver;
    TestUtils utils;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.utils = new TestUtils(driver,10);
    }
    @FindBy(how = XPATH,xpath="//h5[text()='Book Store Application']/ancestor::div[@class='card mt-4 top-card']")
    WebElement bookStoreCard;

    public void gotToBookStore(){
        utils.safeClick(bookStoreCard);
    }


}
