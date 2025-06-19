package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
     * This method closes the browser if it is opene
     * will check if the driver is not null before attempting to quit the browser
     */
    public static void closeBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

    /**
     * This method sends a text to the documented webelement, optionally clearing it first
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
     * This method sends a text to the documented webelement, optionally clearing it first
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
     * This method waits for an element to be clickable
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
     * This method clicks on the element after waiting for it to be clickable
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

    public void jsClick(WebElement element){

    }



}
