package testaudiobooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class AllAudioBooks {
    private static Logger Log = LogManager.getLogger(AllAudioBooks.class);

    WebDriver driver;
    WebDriverWait waiter;

    public AllAudioBooks(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_loader = By.cssSelector("div.js-page-loader.page-loader");
    By loc_booklinks = By.cssSelector("a.lego-book__cover.js-image-block");
    By loc_logo = By.cssSelector("div.n-header-cell.n-header-cell--logo");
    By loc_bottomelem = By.cssSelector("div.phone");
    By loc_bottomelem2 = By.cssSelector("span.likely__button.likely__button_vkontakte");

   public void waitForPageIsLoaded (WebDriver driver) throws Exception
   {
       try {
           Log.info("Wait for allbooks/?booktype=audiobook page is loaded");
           waiter
                   .until(ExpectedConditions.visibilityOfElementLocated(loc_logo));
       } catch (Exception e) {
           Log.error("Error - can't load the page: allbooks/?booktype=audiobook ", e);
           throw e;
       }
       Log.info("Page allbooks/?booktype=audiobook is opened successfully");


   }


    public List<WebElement> scrollToEnd() throws Exception {
        List<WebElement> elements = Collections.EMPTY_LIST;
        WebElement loader;
        int size;
        Actions actions = new Actions(driver);
        WebDriverWait longWaiter = new WebDriverWait(driver, 4);

        do {
            size = elements.size();
            actions.moveToElement(driver.findElement(loc_bottomelem)).perform();
            elements = driver.findElements(loc_booklinks);
            loader = driver.findElement(loc_loader);
            String style = driver.findElement(loc_loader).getAttribute("style");
            Log.info(">>> style:" + style+ ", isDisplayed " + loader.isDisplayed());

         if (loader.isDisplayed()){
             try {
                 actions.moveToElement(driver.findElement(loc_bottomelem2)).perform();
                 Log.info("Wait for Loader is NOT visible");
                 longWaiter
                         .until(ExpectedConditions.invisibilityOfElementLocated(loc_loader));
                 Log.info("Loader is NOT visible");

             } catch (Exception e) {
                 Log.error("Loader is still visible", e);
                 throw e;
             }
         }
         else{
             try {
                 waiter
                         .until(ExpectedConditions.visibilityOfElementLocated(loc_loader));
                 Log.info("Loader have become visible. Going to wait for Loader will be invisible");

             } catch (Exception e) {
                 Log.info("Loader is already NOT visible", e);
                 break;
             }

             try {
                 longWaiter
                         .until(ExpectedConditions.invisibilityOfElementLocated(loc_loader));
                 Log.info("Loader is NOT visible");

             } catch (Exception e) {
                 Log.error("Loader is still visible", e);
                 throw e;
             }
         }

/*
            try {
                Log.info("Wait for loader is absent");
                longWaiter
                        .until(d -> {
                            String style = d.findElement(loc_loader).getAttribute("style");
                            Log.info(">>> style:" + style);
                            return "display: none;".equals(style);
                        });
                Log.info("Loader is NOT visible");

            } catch (Exception e) {
                Log.error("Loader is still visible", e);
                throw e;
            }
*/



            Log.info("size = " + size + " elements.size() = " + elements.size());
        } while (size < elements.size());

        Log.info("Scroll complete, elements.size() = " + elements.size());
        return elements;

    }



}
