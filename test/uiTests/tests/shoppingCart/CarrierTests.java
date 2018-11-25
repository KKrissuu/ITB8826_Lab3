package uiTests.tests.shoppingCart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uiTests.pageObjects.AuthenticationPage;
import uiTests.pageObjects.MainPage;
import uiTests.pageObjects.ProductsListPage;
import uiTests.pageObjects.ShoppingCartPage;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CarrierTests {
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
    public void emptyCart(){
        mainPage.cartButton.click();
        shoppingCartPage.heading.waitUntil(Condition.exist, 12000);
        shoppingCartPage.cancelOrderButton.scrollTo().click();
    }

    @Test
    public void singleTaimmaterjalProductInCartTest(){
        mainPage.searchProduct("Petunia grandiflora Pacta Parade Burgundy");
        productsListPage.orderFirstProduct();

        mainPage.cartButton.click();
        shoppingCartPage.transportButton.should(Condition.hidden);
    }

    @Test
    public void singleNotTaimmaterjalProductInCartTest(){
        mainPage.searchProduct("Lilletugi 36mm x 41cm");
        productsListPage.orderFirstProduct();

        mainPage.cartButton.click();
        shoppingCartPage.transportButton.should(Condition.visible);
    }

    @Test
    public void multipleCategoryProductsInCartTest(){
        mainPage.searchProduct("Petunia grandiflora Pacta Parade Burgundy");
        productsListPage.orderFirstProduct();
        open("https://tellihorticom.koik.ee/");
        mainPage.searchProduct("Lilletugi 36mm x 41cm");
        productsListPage.orderFirstProduct();


        mainPage.cartButton.click();

        shoppingCartPage.stockTestListItem = shoppingCartPage.stockTestItem.parent().parent();
        shoppingCartPage.stockTestListItemID = shoppingCartPage.stockTestListItem.attr("data-id"); //getAttribute("data-id");
        shoppingCartPage.changeProductWeekTrigger = $(byCssSelector(".product_"+shoppingCartPage.stockTestListItemID+" .change_week"));
        shoppingCartPage.changeProductWeekTrigger.click();

        $(byCssSelector(".product_"+shoppingCartPage.stockTestListItemID+" .order_weeks_container select")).selectOptionContainingText("N6 (04.02 - 08.02)");
        $(byCssSelector(".product_"+shoppingCartPage.stockTestListItemID+" .order_week_buttons .save")).scrollTo().click();

        shoppingCartPage.transportButton.shouldNot(Condition.visible);

        $(byCssSelector(".product_"+shoppingCartPage.stockTestListItemID+" .delete")).scrollTo().click();

        shoppingCartPage.transportButton.should(Condition.hidden);
    }

    @Test
    public void emptyCartTest(){
        mainPage.cartButton.click();
        shoppingCartPage.transportButton.shouldNot(Condition.visible);
    }
}
