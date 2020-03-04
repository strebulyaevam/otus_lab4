package driverconfig;

import org.openqa.selenium.WebDriver;

public class SelectDriver {
    private Driver mydriver;

    public void setUpDriver(Driver mydriver){
        this.mydriver = mydriver;
    };
    public WebDriver newDriver(){
        return mydriver.getDriver();
    }

 }
