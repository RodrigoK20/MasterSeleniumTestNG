package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector("td[class='product-name']");

    private final By checkOutBtn = By.cssSelector(".checkout-button");

    private final By cartHeading = By.cssSelector(".has-text-align-center");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading, "Cart"));
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public CheckoutPage clickCheckOutBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        return new CheckoutPage(driver);
    }

    //Table products name elements
    private String getProductNameElements(String productName){
      By element =  By.xpath("//a[normalize-space()='"+ productName +"']");
      return  wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    public List<String> getProductsTable(List<String> productsCart){

        List<String> valuesName = new ArrayList<>();
        for(int i = 0; i < productsCart.size(); i++){
            valuesName.add(getProductNameElements(productsCart.get(i)));
        }
        return valuesName;
    }
}
