package utils;/*
 * @created 11/03/2025 - 2:35 PM
 * @author Dinu

 * Provides a comprehensive wrapper for Selenium WebDriver actions with enhanced reliability and error handling.
 * This utility class encapsulates common Selenium operations with built-in waits, logging, and error management
 * to improve test stability and maintainability.
 */

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static browserFactory.DriverFactory.getDriver;

public class SeleniumActions {
    //used to find element
    public static WebElement findElement(By identifier) {
        waitUntilDisplayed(identifier);
        return getDriver().findElement(identifier);
    }
    // used to wait until an element is displayed using By class
    public static void waitUntilDisplayed(By identifier) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
    }

    // used to wait until an element is displayed using web element
    public static void waitUntilDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait for element to be clickable
    public static void waitUntilClickable(By identifier) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(identifier));
    }

    //wait for element to be clickable
    public static void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // used to click an element
    public static void clickElement(By element){
        waitUntilClickable(element);
        findElement(element).click();
    }
    // used to click an element
    public static void clickElement(WebElement element){
        waitUntilClickable(element);
        element.click();
    }
    //used to mouse hover
    public static void mouseHover(By identifier){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(findElement(identifier)).build().perform();
    }
    //used to mouse hover
    public static void mouseHover(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).build().perform();
    }
    //used to enter text
    public static void enterText(By locator, String text){
        waitUntilDisplayed(locator);
        findElement(locator).sendKeys(text);
    }
    //used to enter text
    public static void enterText(WebElement element, String text){
        waitUntilDisplayed(element);
        element.sendKeys(text);
    }
    // returns whether element is displayed or not
    public static boolean isElementDisplayed(By locator){
        boolean flag;
        try {
            flag = findElement(locator).isDisplayed();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    // returns whether element is displayed or not
    public static boolean isElementDisplayed(WebElement element){
        boolean flag;
        try {
            flag = element.isDisplayed();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //used to switch to a new tab
    public static void switchToNewTab(){
        String parent = getDriver().getWindowHandle();
        Set<String> windows = getDriver().getWindowHandles();
        for(String window : windows){
            if(!window.equals(parent)){
                getDriver().switchTo().window(window);
                break;
            }
        }
    }
    //wait until an attribute value changes
    public static void waitUntilAttributeToBe(By identifier, String attributeName, String attributeValue) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.attributeToBe(identifier, attributeName, attributeValue));
    }
    //wait until an attribute value changes
    public static void waitUntilAttributeToBe(WebElement element, String attributeName, String attributeValue) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
        wait.until(ExpectedConditions.attributeToBe(element, attributeName, attributeValue));
    }
    // click using javascript executor
    public static void jsClickElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }
    // used for switching frames
    public static void switchToFrame(By identifier){
        getDriver().switchTo().frame(findElement(identifier));
    }
    // used for switching frames
    public static void switchToFrame(int index){
        getDriver().switchTo().frame(index);
    }
    // used for switching frames
    public static void switchToFrame(String name){
        getDriver().switchTo().frame(name);
    }
    // used for switching frames back to default
    public static void switchToDefault(){
        getDriver().switchTo().defaultContent();
    }
    //Used to get page title
    public static String getPageTitle(){
        return getDriver().getTitle().trim();
    }
    //used for selecting dropdown
    public static void selectDropDown(By dropdownIdentifier, String option){
        Select select = new Select(findElement(dropdownIdentifier));
        select.selectByVisibleText(option);
    }
    //used for static wait
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException ignored) {
        }
    }
    //used for setting an attribute
    public static void setAttribute(By locator, String attributeName, String attributeValue){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", findElement(locator),
                attributeName, attributeValue);
    }
    // used to get size of web elements
    public static int getSizeOfElements(By identifier) {
        return getDriver().findElements(identifier).size();
    }
    //wait for webelements to be displayed
    public static void waitUntilNumberElementsMoreThanSize(By identifier, int size) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.MEDIUM_WAIT_SECONDS));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(identifier, size));
        } catch (Exception ignored) {
        }
    }
    //get webelements
    public static List<WebElement> findElements(By identifier){
        waitUntilNumberElementsMoreThanSize(identifier, 0);
        return getDriver().findElements(identifier);
    }
    //used to enter test using Actions
    public static void enterTextUsingActions(String text){
        Actions actions = new Actions(getDriver());
        actions.sendKeys(text).build().perform();
    }
    //used to enter test using Actions
    public static void enterTextUsingActions(By locator, String text){
        Actions actions = new Actions(getDriver());
        actions.sendKeys(findElement(locator), text).build().perform();
    }
    //Used to get timestamp
    public static String getTimeStamp(String dateFormat){
        return new SimpleDateFormat(dateFormat).format(new Date());
    }
    //Used to capture screenshot
    @Attachment
    public static String captureScreenshot(WebDriver driver, String screenshotName){
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots"+File.separator+screenshotName+getTimeStamp("yyyy_MM_dd_HH_mm_ss")+".png");
        try {
            FileUtils.copyFile(source, screenshot);
            return screenshot.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
