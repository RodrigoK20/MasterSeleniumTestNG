package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

import java.sql.Driver;

public class DriverManager {
    public WebDriver initializeDriver(){

        WebDriver driver;
        String browser = System.getProperty("browser", "CHROME");

        switch(DriverType.valueOf(browser)){
            case CHROME:
                //Chrome Driver Path
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rodrigo.Viscarra\\Desktop\\MasterSeleniumFramework\\src\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                //Gecko Driver path
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Rodrigo.Viscarra\\Desktop\\MasterSeleniumFramework\\src\\driver\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalidad browser name: " + browser);
        }


        driver.manage().window().maximize();
        return driver;

        //Implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
}
