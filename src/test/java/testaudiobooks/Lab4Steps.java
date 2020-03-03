package testaudiobooks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.io.FileWriter;
import java.util.List;


public class Lab4Steps {

    private static Logger Log = LogManager.getLogger(Lab4Steps.class);

    WebDriver driver;


    @BeforeClass
    public void init(){

        try{
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } catch (Exception e){
            Log.fatal("New driver for Chrome browser isn't created");
            Assert.fail();
        }
    }


    @Test
    public void getAllAudioBooks() throws Exception {
        String hostname = "https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook";

        TestHelper.getURL(driver, hostname);
        AllAudioBooks allAudioBooks = new AllAudioBooks(driver);
        allAudioBooks.waitForPageIsLoaded(driver);
        List<String> links = allAudioBooks.findAllLinks();
        Log.info("Found links: " + links.size());

        Book book = new Book(driver);

        final String[] HEADERS = {"Link", "Title", "Author", "PriceForAudioVer", "LinkToFragment"};
        FileWriter fileWriter = new FileWriter("books.csv");
        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            int i = 0;
            for (String link : links) {
                TestHelper.getURL(driver, link);
                book.printInfo(printer);
                Log.info("Processed " + (++i) + " of " + links.size());

                // this is for debug
                /*
                if (i > 10)
                    break;
                 */
            }
        }
    }


    @Parameters({"browser"})
    @AfterClass
    public void quitBrowser (String browser) {
        if(driver!=null){
            Log.info("Quit from " + browser);
            driver.quit();
        }
    }
}
