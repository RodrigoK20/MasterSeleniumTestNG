package org.selenium.pom.factory;

import com.google.auto.service.AutoService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.selenium.pom.constants.DriverType;

import java.io.File;
import java.sql.Driver;

public class DriverManager {
    public WebDriver initializeDriver(){

        WebDriver driver;
        String browser = System.getProperty("browser", "CHROME");
        String currentWorkingDirChrome = System.getProperty("user.dir");
        String chromePath = File.separator + "src" + File.separator + "driver" + File.separator + "chromedriver.exe";
        String firefoxPath = File.separator + "src" + File.separator + "driver" + File.separator + "geckodriver.exe";

        switch(DriverType.valueOf(browser)){
            case CHROME:
                //Chrome Driver Path
                System.setProperty("webdriver.chrome.driver", currentWorkingDirChrome + chromePath);
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                //Gecko Driver path
                System.setProperty("webdriver.gecko.driver", currentWorkingDirChrome + firefoxPath);
                driver = new FirefoxDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
        return driver;

        //Implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
}
