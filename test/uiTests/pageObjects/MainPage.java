package uiTests.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement searchBox = $(byId("woocommerce-product-search-field-1"));

    public SelenideElement cartButton = $(byClassName("mini_cart"));

    public MainPage searchProduct(String product){
        searchBox.click();
        searchBox.setValue(product).pressEnter();
        return this;
    }

    public MainPage goToCart(){
        cartButton.click();
        return this;
    }
}
