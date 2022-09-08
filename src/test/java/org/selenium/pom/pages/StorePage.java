package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class StorePage extends BasePage {

    private final By searchFld = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");

    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    private final By productsTable = By.cssSelector(".products.columns-4");

    private final By tagTable = By.tagName("h2");

    private final By quantityFld = By.name("cart[b571ecea16a9824023ee1af16897a582][qty]");

    private final By updateCartBtn = By.cssSelector("button[value='Update cart']");

    private final By cartMessage = By.cssSelector("div[role='alert']");

    private final By cartItemsNumberFld = By.cssSelector("div[class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container'] span[class='count']");
    private final By cartIconBtn = By.cssSelector("div[class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container'] div[class='ast-site-header-cart-li current-menu-item']");
    public StorePage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
       return  wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage enterTextInSearchFld(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
        return this;
    }

    public StorePage clickSearchBtn(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCartBtnElement(String productName){
      return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        return new CartPage(driver);
    }

    public int numberOfProducts(){

        WebElement tableProducts = wait.until(ExpectedConditions.visibilityOfElementLocated(productsTable));
        List<WebElement> totalProducts = tableProducts.findElements(tagTable);

        return totalProducts.size();
    }

    public List<String> productsNames(){

        List<String> objectToStore = new ArrayList<>();
        WebElement tableProducts = wait.until(ExpectedConditions.visibilityOfElementLocated(productsTable));
        List<WebElement> totalProducts = tableProducts.findElements(tagTable);

        for(int i = 0; i < totalProducts.size(); i++){
             objectToStore.add(totalProducts.get(i).getText());
        }

        return objectToStore;
    }

    public StorePage updateQuantityProduct(int quantity){

        if(quantity > 0){
        /*    for(int i=0; i< quantity; i++){
                e.sendKeys(Keys.ARROW_UP);
            }*/
            WebElement e =  driver.findElement(quantityFld);
            e.clear();
            e.sendKeys((String.valueOf(quantity)));

        }
        else{
            System.out.println("Error");
        }

        return this;
    }

    public StorePage clickUpdateCart(){
        wait.until(ExpectedConditions.elementToBeClickable(updateCartBtn)).click();
        return this;
    }

    public String messageAlertCart(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartMessage)).getText();
    }

    public int itemsCart(){
       int value = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemsNumberFld)).getText());
       return value;
    }

    private By getAddToCartBtnElements(String productName){

        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtnItems(List<String> productsName) throws InterruptedException {
        for(int i=0; i < productsName.size(); i++){
            System.out.println(productsName.get(i));
            By addToCartBtn = getAddToCartBtnElements(productsName.get(i));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

        }

        Thread.sleep(4000);
        return this;
    }

    public CartPage clickViewCartIcon(){

        wait.until(ExpectedConditions.elementToBeClickable(cartIconBtn)).click();
        return new CartPage(driver);
    }

}
