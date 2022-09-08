package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    private final By userNameFld = By.cssSelector("#username");

    private final By passwordFld = By.cssSelector("#password");

    private final By showLoginBtn = By.cssSelector(".showlogin");

    private final By loginBtn = By.cssSelector("button[value='Login']");

    private final String value = "value";

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By countryDropDown = By.id("billing_country");

    private final By stateDropDown = By.id("billing_state");

    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By alternateCountryDropDown = By.id("select2-billing_country-container");

    private final By alternateStateDropDown = By.id("select2-billing_state-container");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    //guestCheckoutUsingDirectBankTransfer
    public CheckoutPage enterFirstName(String firstName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLine(String addressLine){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLine);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        e.clear();
        e.sendKeys(billingCity);
        return this;
    }

    public CheckoutPage enterBillingPostCode(String billingPostCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
        e.clear();
        e.sendKeys(billingPostCode);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(billingEmail);
        return this;
    }

    //Country dropdown
    public CheckoutPage selectCountry(String countryName){
        //  Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropDown)));
        // select.selectByVisibleText(countryName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+ countryName +"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    //State dropdown
    public CheckoutPage selectState(String stateName){
       // Select select = new Select(driver.findElement(stateDropDown));
        // select.selectByVisibleText(stateName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+ stateName +"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage clickPlaceOrder(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    //Checkout data
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLine(billingAddress.getAddresssLineOne()).
                enterBillingCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterBillingPostCode(billingAddress.getPostalCode()).
                enterBillingEmail(billingAddress.getEmail());
    }

    //loginAndCheckoutUsingDirectBankTransfer
    public CheckoutPage showLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(showLoginBtn)).click();
        return this;
    }

    public CheckoutPage userLogin(User user){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(user.getUsername());
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(user.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public String validateLogin(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld)).getAttribute(value);
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }

        return this;
    }

}
