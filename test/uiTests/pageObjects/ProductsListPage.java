package uiTests.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductsListPage {
    public SelenideElement firstProductOrderingButton = $(byCssSelector("ul.products .btn"));

    public ProductsListPage orderFirstProduct(){
        firstProductOrderingButton.waitUntil(Condition.exist, 12000);
        firstProductOrderingButton.click();
        sleep(1000);
        return this;
    }
}
