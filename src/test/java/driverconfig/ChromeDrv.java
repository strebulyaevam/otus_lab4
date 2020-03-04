package driverconfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ChromeDrv implements Driver {

    private static Logger Log = LogManager.getLogger(ChromeDrv.class);

    public WebDriver getDriver() {
        WebDriver driver = null;
        try{
            WebDriverManager.chromedriver().setup();
            driver = new org.openqa.selenium.chrome.ChromeDriver();
            return driver;
        } catch (Exception e){
            Log.fatal("New driver for Chrome browser isn't created");
            Assert.fail();
        }

        return driver;
    }
 }
