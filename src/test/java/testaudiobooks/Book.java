package testaudiobooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Book {

    private static Logger Log = LogManager.getLogger(Book.class);
    private static Logger LogBookInfo = LogManager.getLogger(Book.class);

    WebDriver driver;
    WebDriverWait waiter;

    public Book(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_title = By.cssSelector("div.l-book-description.position-top div.b-headers span");
    By loc_author = By.xpath("(//span[@class='author active'])[1]");
    By loc_price = By.xpath("(//div[@data-type='audiobook']//div[@class ='p-tab__price ng-binding']//nobr)[1]");
//    By loc_lincfragment = By.xpath("(//div[@class = 'nkk-audio-excerpts nkk-download-block ng-scope']//div[@class = 'p-book-download-link m-audio-mp3 ng-scope']//a[@class='ng-binding'])[1]");
    By loc_lincfragment = By.xpath("(//a[@class='nkk-file-download__link'])[1]");

    public void printInfo ()
    {
        try {
            Log.info("Try to get info from link - " + driver.getCurrentUrl());
            waiter.until(ExpectedConditions.presenceOfElementLocated(loc_title));

            LogBookInfo.info("--------------Book--------------");
            LogBookInfo.info("Link - " + driver.getCurrentUrl());
            LogBookInfo.info("Title - " + driver.findElement(loc_title).getText());
            LogBookInfo.info("Author - " + driver.findElement(loc_author).getText());
            LogBookInfo.info("Price for audio ver - " + driver.findElement(loc_price).getText());
            LogBookInfo.info("Link to fragment - " + driver.findElement(loc_lincfragment).getAttribute("href"));
            LogBookInfo.info("--------------------------------");
            Log.info("Info about book " + driver.getCurrentUrl() + "has been got successfully");

        } catch (Exception e) {
            Log.error("Get info about book " + driver.getCurrentUrl() + "is failed", e);
//            throw e;
        }
    }
}
