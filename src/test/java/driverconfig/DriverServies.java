package driverconfig;

import config.Lab4Config;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverServies {
    private final WebDriver driver;
    Lab4Config cfg;
    SelectDriver selectDriver = new SelectDriver();
    private static Logger Log = LogManager.getLogger(DriverServies.class);

    public DriverServies () {
        cfg = ConfigFactory.create(Lab4Config.class);
        if(cfg.browser().equals("Chrome")) {
            Log.info("Select Chrome driver");
            selectDriver.setUpDriver(new ChromeDrv());
        }
        if(cfg.browser().equals("FireFox")) {
            Log.info("Select FireFox driver");
            selectDriver.setUpDriver(new FFDrv());
        }
        driver = selectDriver.newDriver();
        Log.info("Driver has been created successfully");
    }

    public WebDriver getDriver() {
        driver.manage().timeouts().implicitlyWait(4L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public void closeDriver(){
        if(driver!=null){
            Log.info("Quit from browser");
            driver.quit();
        }
    }
}
