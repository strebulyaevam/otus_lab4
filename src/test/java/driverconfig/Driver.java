package driverconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public interface Driver {
    static Logger Log = LogManager.getLogger(Driver.class);

    WebDriver getDriver();

}
