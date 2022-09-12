package org.selenium.pom.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

        String screenShotDir = "src" + File.separator + "screenshots" + File.separator;


        try{

        HomePage homePage = new HomePage(driver).load();
        Product product = new Product(1215);
        String searchFor = "Blue";

        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.enterTextInSearchFld(searchFor).clickSearchBtn();

        System.out.println(storePage.numberOfProducts());
        System.out.println(storePage.productsNames());
        Assert.assertEquals(storePage.getTitle(), "Search results: “"+ searchFor +"”");
        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.clickViewCart();
        cartPage.isLoaded();

        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckOutBtn();


        //Checkout form without constructor
        //BillingAddress billingAddress = new BillingAddress().
/*            setFirstName("Name1").
            setLastName("Last name").
            setAddresssLineOne("Address street 1").
            setCity("San Francisco").
            setPostalCode("94188").
            setEmail("hola123@gmail.com");*/

        //Checkout form with constructor
        //BillingAddress billingAddress = new BillingAddress("Name1", "Last name", "Address street 1", "San Francisco", "94188", "hola123@gmail.com");

        //Checkout using Jackson (JSON file)
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);


            checkoutPage.setBillingAddress(billingAddress).
                    selectDirectBankTransfer().
                    clickPlaceOrder();

            Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

            extent.createTest("guestCheckoutUsingDirectBankTransfer")
                    .log(Status.PASS, "This is a test event for guestCheckoutUsingDirectBankTransfer, and it passed!")
                    .assignAuthor("RV")
                    .assignCategory("Regression test");
        }
        catch (AssertionError e){
            // e.printStackTrace();
            extent.createTest("guestCheckoutUsingDirectBankTransfer")
                    .log(Status.FAIL, "This is a test event for guestCheckoutUsingDirectBankTransfer, and it failed!")
                    .assignAuthor("RV")
                    .addScreenCaptureFromPath(screenShotDir + "MyFirstTestCase_guestCheckoutUsingDirectBankTransfer.png")
                    .fail(MediaEntityBuilder.createScreenCaptureFromPath(screenShotDir).build())
                    .assignCategory("Regression test");
            throw new IllegalStateException(e);

        }

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

       HomePage homePage = new HomePage(driver).load();
       String searchFor = "Blue";
       StorePage storePage = homePage.navigateToStoreUsingMenu();

       storePage.enterTextInSearchFld(searchFor).clickSearchBtn();

       System.out.println(storePage.numberOfProducts());
       System.out.println(storePage.productsNames());
       Assert.assertEquals(storePage.getTitle(), "Search results: “"+ searchFor +"”");
       storePage.clickAddToCartBtn("Blue Shoes");

       CartPage cartPage = storePage.clickViewCart();

       Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
       CheckoutPage checkoutPage = cartPage.clickCheckOutBtn();

       checkoutPage.showLogin();

       //Login using Object class
       User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
       checkoutPage.userLogin(user);

       Assert.assertEquals(checkoutPage.validateLogin(), "email123test@gmail.com");

       //Checkout using Jackson (JSON file)
       BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);

       checkoutPage.setBillingAddress(billingAddress).
               selectDirectBankTransfer().
               clickPlaceOrder();

       Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }

}
