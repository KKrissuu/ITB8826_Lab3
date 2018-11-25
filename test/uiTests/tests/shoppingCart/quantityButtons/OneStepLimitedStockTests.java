package uiTests.tests.shoppingCart.quantityButtons;

import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uiTests.pageObjects.AuthenticationPage;
import uiTests.pageObjects.MainPage;
import uiTests.pageObjects.ProductsListPage;
import uiTests.pageObjects.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.open;

public class OneStepLimitedStockTests {
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
    public void setCart(){
        mainPage.cartButton.click();
        shoppingCartPage.heading.waitUntil(Condition.exist, 12000);
        shoppingCartPage.cancelOrderButton.scrollTo().click();

        mainPage.searchProduct("Kastekann Garden 5L roheline");
        productsListPage.orderFirstProduct();
        mainPage.cartButton.click();
    }

    @Before
    public void resetQuantity(){
        shoppingCartPage.setAmount("1");
    }

    @Test
    public void clickPlusMarkTest(){
        shoppingCartPage.clickOnPlus().checkAmount("2");
    }

    @Test
    public void clickMinusMarkTest(){
        shoppingCartPage.clickOnMinus().checkAmount("1");
    }

    @Test
    public void clickPlusFiveTimesTest(){
        shoppingCartPage.clickOnPlusTimes(5).checkAmount("6");
    }

    @Test
    public void amountSmallerThanLimitedStockTest(){
        shoppingCartPage.setAmount("271");
        shoppingCartPage.clickOnPlus().checkAmount("272");
        shoppingCartPage.amountErrorMessage.shouldNotHave(Condition.text("Toodet on laos alles"));
    }

    @Test
    public void amountEqualsLimitedStockTest(){
        shoppingCartPage.setAmount("272");
        shoppingCartPage.clickOnPlus().checkAmount("273");
        shoppingCartPage.amountErrorMessage.shouldNotHave(Condition.text("Toodet on laos alles"));
    }

    @Test
    public void amountBiggerThanLimitedStockTest(){
        shoppingCartPage.setAmount("273");
        shoppingCartPage.clickOnPlus();
        shoppingCartPage.amountErrorMessage.should(Condition.matchText("Toodet on laos alles 273"));
    }
}
