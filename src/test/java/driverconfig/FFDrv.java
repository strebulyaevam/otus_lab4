package driverconfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FFDrv implements Driver {
    private static Logger Log = LogManager.getLogger(ChromeDrv.class);

    public WebDriver getDriver() {
        WebDriver driver = null;
        try{
            WebDriverManager.firefoxdriver().setup();
            driver = new org.openqa.selenium.firefox.FirefoxDriver();
            return driver;
        } catch (Exception e){
            Log.fatal("New driver for FireFox browser isn't created");
            Assert.fail();
        }
        return driver;
    }

}
