package testaudiobooks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Book {

    WebDriver driver;
    WebDriverWait waiter;

    public Book(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_title = By.cssSelector("div.l-book-description.position-top div.b-headers span");
    By loc_author = By.cssSelector("div.authors span.author.active"); // возвращает много, нужно взять первый элемент
    By loc_price = By.cssSelector("div[data-type='audiobook'] div.p-tab__price.ng-binding nobr"); // возвращает много, нужно взять первый элемент
    By loc_lincfragment = By.cssSelector("(//div[@class = 'nkk-audio-excerpts nkk-download-block ng-scope']//div[@class = 'p-book-download-link m-audio-mp3 ng-scope']//a[@class='ng-binding'])[1]");
}
