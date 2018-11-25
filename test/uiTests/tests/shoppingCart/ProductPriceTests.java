package uiTests.tests.shoppingCart;

import com.codeborne.selenide.Condition;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uiTests.pageObjects.AuthenticationPage;
import uiTests.pageObjects.MainPage;
import uiTests.pageObjects.ProductsListPage;
import uiTests.pageObjects.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.open;

public class ProductPriceTests {
    static AuthenticationPage authenticationPage = new AuthenticationPage();
    MainPage mainPage = new MainPage();
    ProductsListPage productsListPage = new ProductsListPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    @BeforeClass
    public static void login(){
        open("https://tellihorticom.koik.ee/");
        authenticationPage.loginWithDefaultUser();
    }

    @Before
    public void resetCart(){
        mainPage.cartButton.click();
        shoppingCartPage.heading.waitUntil(Condition.exist, 12000);
        shoppingCartPage.cancelOrderButton.scrollTo().click();
    }

    @AfterClass
    public static void logOut(){
        authenticationPage.logOut();
    }

    @Test
    public void unitPriceNotEndingWithZeroTest(){
        mainPage.searchProduct("Osteospermum ecklonis Tradewinds Light Purple");
        productsListPage.orderFirstProduct();
        mainPage.cartButton.click();

        shoppingCartPage.productUnitPrice.should(Condition.matchesText("0.323 €"));
        shoppingCartPage.productTotalPrice.should(Condition.matchesText("40.698 €"));
    }

    @Test
    public void unitPriceEndingWithZeroTest(){
        mainPage.searchProduct("Kartul Baltic Rose");
        productsListPage.orderFirstProduct();
        mainPage.cartButton.click();

        shoppingCartPage.productUnitPrice.should(Condition.matchesText("2.590 €"));
        shoppingCartPage.productTotalPrice.should(Condition.matchesText("2.59 €"));
    }

    @Test
    public void quantityChangeTest(){
        mainPage.searchProduct("Osteospermum ecklonis Tradewinds Light Purple");
        productsListPage.orderFirstProduct();
        mainPage.cartButton.click();

        shoppingCartPage.clickOnPlus();
        shoppingCartPage.productUnitPrice.should(Condition.matchesText("0.323 €"));
        shoppingCartPage.productTotalPrice.should(Condition.matchesText("81.396 €"));
    }

}
