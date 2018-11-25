package uiTests.tests.shoppingCart.quantityButtons;

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

public class BigStepUnlimitedStockTests {
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

        mainPage.searchProduct("Primula vulgaris Unistar F1 Mix 275tk/kassetis");
        productsListPage.orderFirstProduct();
        mainPage.cartButton.click();
    }

    @Before
    public void resetQuantity(){
        shoppingCartPage.setAmount("275");
    }

    @AfterClass
    public static void logOut(){
        authenticationPage.logOut();
    }

    @Test
    public void clickPlusMarkTest(){
        shoppingCartPage.clickOnPlus().checkAmount("550");
    }

    @Test
    public void clickMinusMarkTest(){
        shoppingCartPage.clickOnMinus().checkAmount("275");
    }

    @Test
    public void clickPlusFiveTimesTest(){
        shoppingCartPage.clickOnPlusTimes(5).checkAmount("1650");
    }

    @Test
    public void insertBigAmountTest(){
        shoppingCartPage.setAmount("352342342423432332423");
        shoppingCartPage.amountErrorMessage.shouldNotHave(Condition.text("Toodet on laos alles"));
    }
}
