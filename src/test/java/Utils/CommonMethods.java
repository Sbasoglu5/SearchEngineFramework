package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication() {

        ConfigReader.readProperties();

        String browserType=ConfigReader.getPropertyValue("browserType");
        switch (browserType){
            case "Chrome":
                ChromeOptions ops = new ChromeOptions();
                /*ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");*/
                if(ConfigReader.getPropertyValue("Headless").equals("true")){
                    ops.addArguments("--headless=new");
                }
                driver=new ChromeDriver(ops);
                break;
            case "Firefox":
                driver=new FirefoxDriver();
                break;
            case "IE":
                driver=new InternetExplorerDriver();
                break;
            default:
                EdgeOptions edgeOps=new EdgeOptions();
                if(ConfigReader.getPropertyValue("Headless").equals("true")){
                    edgeOps.addArguments("--headless=new");
                }
                driver=new EdgeDriver(edgeOps);
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.IMPLICIT_WAIT));
        initializePageObjects();
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("Beginning of the test case");


    }
    public static void closeBrowser(){
        Log.endTestCase("End of the test case");
        driver.close();
    }
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void doClick(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static void sendText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }


    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }
    public static Select clickOnDropdown(WebElement element){
        Select select=new Select(element);
        return select;
    }

    public static void selectByValue(WebElement element , String value){
        clickOnDropdown(element).selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element , String text){
        clickOnDropdown(element).selectByVisibleText(text);
    }
    public static void selectByIndex(WebElement element, int index){
        clickOnDropdown(element).selectByIndex(index);
    }

    public static void selectByOptions(WebElement element, String text){
        List<WebElement> options=clickOnDropdown(element).getOptions();
        for (WebElement option:options){
            String ddlOptionText=option.getText();
            if (ddlOptionText.equalsIgnoreCase(text)){
                option.click();
                break;
            }
        }
    }

    public static String getTimeStamp(String pattern){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static byte[] takeScreenshot(String imageName){
        //this casts the webDriver instance 'driver' to TakesScreenshot Interface
        TakesScreenshot ts=(TakesScreenshot)driver;

        //this captures the screenshot and stores it as byte array
        byte [] picBytes=ts.getScreenshotAs(OutputType.BYTES);

        //this captures the screenshot and stores it as a file in the sourceFile variable
        File sourcePath=ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourcePath,new File(Constants.SCREENSHOT_FILEPATH+imageName+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return picBytes;
    }
    public static void switchToFrame(String nameOrId){
        try {
            driver.switchTo().frame(nameOrId);
        }catch (java.util.NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public static void switchToFrame(WebElement element){
        try {
            driver.switchTo().frame(element);
        }catch (java.util.NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public static void switchToFrame(int index){
        try {
            driver.switchTo().frame(index);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public static void submit(WebElement element){
        element.submit();
    }
}
