package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

   // protected WebDriverWait waitLong, waitShort;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void load(String endPoint){
       // driver.get("https://askomdch.com" + endPoint);
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    //Overlay icon animation
    public void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAYS SIZE:" + overlays.size());
        if(overlays.size() > 0){
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
            System.out.println("OVERLAYS INVISIBLE");
        }
        else{
            System.out.println("OVERLAY NOT FOUND");
        }
    }

    //Method used for wait strategies
   // public WebElement waitForElementToBeVisible(By element){
     //  return  wait.until(ExpectedConditions.visibilityOfElementLocated(element));
   // }

}
