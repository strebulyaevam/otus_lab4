package testaudiobooks;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    By loc_bottomelem2 = By.cssSelector("span.p-offer__desc");
    By loc_bottomelem = By.cssSelector("span.likely__button.likely__button_vkontakte");

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


    public List<String> findAllLinks() throws Exception {
        List<WebElement> elements = Collections.EMPTY_LIST;
        WebElement loader;
        Actions actions = new Actions(driver);

        do {
            actions.moveToElement(driver.findElement(loc_bottomelem)).perform();
            loader = driver.findElement(loc_loader);
            String style = driver.findElement(loc_loader).getAttribute("style");
            Log.info(">>> style:" + style+ ", isDisplayed " + loader.isDisplayed());

            if (loader.isDisplayed()) {
                actions.moveToElement(driver.findElement(loc_bottomelem2)).perform();
                continue;
            } else {
                try {
                    waiter.until(ExpectedConditions.visibilityOfElementLocated(loc_loader));
                    Log.info("Loader have become visible. Going to wait for Loader will be invisible");
                    actions.moveToElement(driver.findElement(loc_bottomelem2)).perform();
                    continue;

                } catch (Exception e) {
                    Log.info("Loader is already NOT visible", e);
                    break;
                }
            }
        } while (true);

        elements = driver.findElements(loc_booklinks);
        Log.info("Scroll complete, elements.size() = " + elements.size());

        if (elements == null)
            return Collections.EMPTY_LIST;

        List<String> links = new ArrayList<>();
        for (WebElement element : elements) {
            String link = element.getAttribute("href");
            if (link != null && link.length() > 0) {
                links.add(link);
            }
        }

        return links;
    }



}
