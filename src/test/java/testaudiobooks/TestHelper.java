package testaudiobooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestHelper {

    private static Logger Log = LogManager.getLogger(TestHelper.class);


    public static void getURL(WebDriver driver, String hostname) throws Exception {
        Log.info("Try to get " + hostname);
        driver.manage().window().maximize();

        try {
            driver.get(hostname);
            Log.info(hostname + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + hostname +" isn't available");
            Assert.fail();
        }
    }

    public void clickOnElem (WebDriverWait waiter, By loc_elem, String nameOfElem) throws Exception
    {
        try {
            Log.info("Try to click on " + nameOfElem);
            waiter
                    .until(ExpectedConditions.elementToBeClickable(loc_elem)).click();
        } catch (Exception e) {
            Log.error("Error - " + nameOfElem + " isn't clickable at the page", e);
            throw e;
        }
        Log.info(nameOfElem + " was clicked successfully");
    }


}
