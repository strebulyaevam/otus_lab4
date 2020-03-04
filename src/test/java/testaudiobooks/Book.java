package testaudiobooks;

import config.Lab4Config;
import driverconfig.DriverServies;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Book {

    private static Logger Log = LogManager.getLogger(Book.class);
    private static Logger LogBookInfo = LogManager.getLogger("reportLogger");

    WebDriver driver;
    WebDriverWait waiter;
    DriverServies driverServies;


    public Book(WebDriver driver, Lab4Config cfg) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, cfg.waittimeout());
    }

    By loc_title = By.cssSelector("div.l-book-description.position-top div.b-headers span");
    By loc_author = By.xpath("(//span[@class='author active'])[1]");
    By loc_price = By.xpath("(//div[@data-type='audiobook']//div[@class ='p-tab__price ng-binding']//nobr)[1]");
//    By loc_lincfragment = By.xpath("(//div[@class = 'nkk-audio-excerpts nkk-download-block ng-scope']//div[@class = 'p-book-download-link m-audio-mp3 ng-scope']//a[@class='ng-binding'])[1]");
    By loc_lincfragment = By.xpath("(//a[@class='nkk-file-download__link'])[1]");

    public void printInfo (CSVPrinter printer)
    {
        try {
            Log.info("Try to get info from link - " + driver.getCurrentUrl());
            waiter.until(ExpectedConditions.presenceOfElementLocated(loc_title));

            String link = driver.getCurrentUrl();
            String title = driver.findElement(loc_title).getText();
            String author = driver.findElement(loc_author).getText();
            String price = driver.findElement(loc_price).getText();
            String linkToFragment = driver.findElement(loc_lincfragment).getAttribute("href");

            //"Link", "Title", "Author", "PriceForAudioVer", "LinkToFragment"
            printer.printRecord(link, title, author, price, linkToFragment);

            LogBookInfo.info("--------------Book--------------");
            LogBookInfo.info("Link - " + link);
            LogBookInfo.info("Title - " + title);
            LogBookInfo.info("Author - " + author);
            LogBookInfo.info("Price for audio ver - " + price);
            LogBookInfo.info("Link to fragment - " + linkToFragment);
            LogBookInfo.info("--------------------------------");
            Log.info("Info about book " + driver.getCurrentUrl() + "has been got successfully");

        } catch (Exception e) {
            Log.error("Get info about book " + driver.getCurrentUrl() + "is failed", e);
//            throw e;
        }
    }
}
