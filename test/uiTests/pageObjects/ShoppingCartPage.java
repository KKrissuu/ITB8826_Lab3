package uiTests.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage {
    public SelenideElement heading = $(byClassName("checkout_heading"));

    public SelenideElement cancelOrderButton = $(byCssSelector(".submit_section .cancel"));

    public SelenideElement transportButton = $(byClassName("shipping_3_wrap"));

    public SelenideElement firstProductQuantityTextbox = $(byCssSelector(".products .amount input"));

    public SelenideElement firstProductIncreaseQuantityButton = $(byCssSelector(".products .amount .increase_custom"));

    public SelenideElement firstProductDecreaseQuantityButton = $(byCssSelector(".products .amount .decrease_custom"));

    public SelenideElement amountErrorMessage = $(byClassName("stock_notice_row"));

    public SelenideElement productUnitPrice = $(byClassName("regular_price"));

    public SelenideElement productTotalPrice = $(byCssSelector(".products .total"));

    public SelenideElement stockTestItem = $(byText("Lilletugi 36mm x 41cm"));
    public SelenideElement stockTestListItem;
    public String stockTestListItemID;
    public SelenideElement changeProductWeekTrigger;

    public ShoppingCartPage cancelOrder(){
        cancelOrderButton.scrollTo().click();
        return this;
    }

    public ShoppingCartPage checkAmount(String amount){
        firstProductQuantityTextbox.should(Condition.value(amount));
        return this;
    }

    public ShoppingCartPage setAmount(String amount){
        firstProductQuantityTextbox.setValue(amount);
        return this;
    }

    public ShoppingCartPage clickOnPlus(){
        firstProductIncreaseQuantityButton.click();
        return this;
    }

    public ShoppingCartPage clickOnMinus(){
        firstProductDecreaseQuantityButton.click();
        return this;
    }

    public ShoppingCartPage clickOnPlusTimes(Integer count){
        for(int i = 0; i < count; i++){
            firstProductIncreaseQuantityButton.click();
        }
        return this;
    }

}
