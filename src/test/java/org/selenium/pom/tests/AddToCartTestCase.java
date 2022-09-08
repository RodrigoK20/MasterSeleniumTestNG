package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartTestCase extends BaseTest {

    @Test
    public void addToCartHomePage() throws IOException {
        HomePage homePage = new HomePage(driver).load();
        Product product = new Product(2201);

        homePage.clickOnAddCartHomePageProduct(product.getName());
        CartPage cartPage = homePage.viewCartPage();
        cartPage.isLoaded();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

    }

    @Test
    public void updateQuantityProduct() throws IOException{
        addToCartHomePage();

        StorePage storePage = new StorePage(driver);
        storePage.updateQuantityProduct(6);

        storePage.clickUpdateCart();

        Assert.assertEquals(storePage.messageAlertCart(), "Cart updated.");

    }

    @Test
    public void validateItemsCart() throws IOException, InterruptedException {

        HomePage homePage = new HomePage(driver).load();
        Product product = new Product(1215);

        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.isLoaded();

        List<String> products = new ArrayList<String>();
        products.add("Blue Shoes");
        products.add("Basic Blue Jeans");
        products.add("Black Over-the-shoulder Handbag");
        products.add("Blue Denim Shorts");

        storePage.clickAddToCartBtnItems(products);

        //Evaluate the items of the cart
        Assert.assertEquals(storePage.itemsCart(),products.size());

        CartPage cartPage = storePage.clickViewCart();
        cartPage.isLoaded();

        //Validate if all the products were added to the table
        List<String> result = cartPage.getProductsTable(products);
        Assert.assertEquals(result, products);

    }
}
