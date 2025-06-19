package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer{

    public static WebDriver driver;

    public void openBrowserAndLaunchApplication(){
        switch (ConfigReader.read("browser")){
            case "Chrome":

                /**
                 * use ChromeOptions when running headless during CI/CD
                 */

               /* ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver=new ChromeDriver(options);*/
                driver=new ChromeDriver();
                break;

            case "FireFox":
                driver=new FirefoxDriver();
                break;

            case "Edge":
                driver=new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        //maximize the window - commenting this out only because screen is too big
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //navigate to Syntax website
        driver.get(ConfigReader.read("url"));

        //initialize page objects (POM)
        initializerPageObjects();

    }

    /**
     * Closes the browser if it is opene
     * will check if the driver is not null before attempting to quit the browser
     */
    public static void closeBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

    /**
     * Sends a text to the documented webelement, optionally clearing it first
     * @param text the text to send
     * @param element the webelement the text will be sent to
     * @param clear will clear the element before sending text
     */
    public static void sendText(String text, WebElement element, boolean clear){
        if(clear){
            element.clear();
        }
        element.sendKeys(text);
    }

    /**
     * Sends a text to the documented webelement, optionally clearing it first
     * @param text the text to send
     * @param element the webelement the text will be sent to
     */
    public static void sendText(String text, WebElement element){
        element.sendKeys(text);
    }

    /**
     * Creates and returns a WebDriverWait object using the driver and an explicit wait timeout.
     *
     * The timeout value is retrieved from the Constants class (EXPLICIT_WAIT), so it can be reused consistently
     * throughout the framework for synchronization purposes.
     *
     * @return WebDriverWait instance
     */
    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    /**
     * Waits for an element to be clickable
     *
     *  Utilizes the getWait() method, which creates a WebDriverWait instance
     *  using the configured WebDriver and the explicit wait timeout defined
     *  in the Constants class.
     * @param element
     */
    public static void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Clicks on the element after waiting for it to be clickable
     * @param element
     */
    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Returns a JavascriptExecutor instance, which allows executing JavaScript commands
     * directly in the browser when standard Selenium actions (like click) fail.
     *
     * This is useful for handling complex or dynamic web elements that are not easily
     * interactable using regular WebDriver methods.
     *
     * Cast WebDriver to JavascriptExecutor to enable JavaScript execution to
     * allow use of executeScript
     *
     * @return
     */
    public JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js= (JavascriptExecutor) driver; //this is narrowing it down from WebDriver to a more
                                                            //specific type (JavascriptExecutor) using type casting
        return js;
    }

    /**
     * Clicks on a WebElement using JavaScript.
     * <p>
     * This is useful when standard Selenium .click() fails due to visibility or DOM issues.
     *
     * @param element the WebElement to be clicked
     */
    public void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }

    /**
     * This method selects an option from the dropdown using the option's value attribute
     * @param value the value attribute of the option to select
     * @param element the dropdown WebElement
     */
    public static void selectByValue(WebElement element, String value){
        Select sel=new Select(element);
        sel.selectByValue(value);

    }

    /**
     * This method selects an option from the dropdown using visible text
     * @param element the dropdown WebElement
     * @param text the visible text of the option to select
     */
    public static void selectByVisibleText(WebElement element, String text){
        Select sel=new Select(element);
        sel.selectByVisibleText(text);
    }

    /**
     * This method selects an option from the dropdown using the index
     * @param element the dropdown WebElement
     * @param index the index of the option to select
     */
    public static void selectByIndex(WebElement element, int index){
        Select sel=new Select(element);
        sel.selectByIndex(index);
    }

    /**
     * Takes a screenshot of the current browser window and saves it in the specified file
     * Also returns the screenshot as a byte array for use in reports or attachments
     * @param fileName the base name to use when saving the screenshot
     * @return a byte array containing the screenshot in PNG format
     */
    public byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try{
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILE_PATH + fileName+" " +
                    getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return picByte;
    }

    public String getTimeStamp(String pattern){
        Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);

    }

}
