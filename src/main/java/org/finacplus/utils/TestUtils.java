package org.finacplus.utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor javascriptExecutor;

    public TestUtils(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.javascriptExecutor = (JavascriptExecutor) driver;
        LogUtils.info("TestUtils initialized with timeout: {} seconds", timeout);
    }

    private WebElement waitForElementToClick(WebElement element) {
        LogUtils.debug("Waiting for element to be clickable: {}", element);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForElementToPresent(WebElement element) {
        LogUtils.debug("Waiting for element to be visible: {}", element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(WebElement element) {
        try {
            boolean present = element.isDisplayed();
            LogUtils.info("Element is present: {}", present);
            return present;
        } catch (Exception e) {
            LogUtils.warn("Element not present or not interactable", e);
            return false;
        }
    }

    public void safeClick(WebElement element) {
        try {
            LogUtils.info("Attempting safe click on element: {}", element);
            waitForElementToClick(element).click();
        } catch (ElementClickInterceptedException e) {
            LogUtils.warn("Click intercepted, scrolling into view and retrying", e);
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                waitForElementToClick(element).click();
                LogUtils.info("Element clicked successfully after scroll","");
            } catch (Exception ex) {
                LogUtils.error("Failed to click element after scrolling", ex);
            }
        }
    }

    public void safeSendKeys(WebElement element, String value) {
        try {
            LogUtils.info("Sending keys to element: {}", value);
            WebElement ele = waitForElementToPresent(element);
            ele.clear();
            ele.sendKeys(value);
        } catch (Exception e) {
            LogUtils.error("Unable to send keys to element", e);
        }
    }

    public String getText(WebElement element) {
        try {
            String text = waitForElementToPresent(element).getText();
            LogUtils.info("Text retrieved: {}", text);
            return text;
        } catch (Exception e) {
            LogUtils.error("Unable to get text from element", e);
            return "";
        }
    }
}

