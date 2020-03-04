package testaudiobooks;

import config.Lab4Config;
import driverconfig.DriverServies;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.aeonbits.owner.ConfigFactory;

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
    Lab4Config cfg;
    DriverServies driverServies;

    @BeforeClass
    public void init(){

        cfg = ConfigFactory.create(Lab4Config.class);
        driverServies = new DriverServies();
    }


    @Test
    public void getAllAudioBooks() throws Exception {
        String hostname = cfg.hostname();

        TestHelper.getURL(driverServies.getDriver(), hostname);
        AllAudioBooks allAudioBooks = new AllAudioBooks(driverServies.getDriver(), cfg);
        allAudioBooks.waitForPageIsLoaded(driverServies.getDriver());
        List<String> links = allAudioBooks.findAllLinks();
        Log.info("Found links: " + links.size());

        Book book = new Book(driverServies.getDriver(), cfg);

        final String[] HEADERS = {"Link", "Title", "Author", "PriceForAudioVer", "LinkToFragment"};
        FileWriter fileWriter = new FileWriter("books.csv");
        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            int i = 0;
            for (String link : links) {
                TestHelper.getURL(driverServies.getDriver(), link);
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
        driverServies.closeDriver();
    }
}
