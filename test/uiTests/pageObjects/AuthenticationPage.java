package uiTests.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class AuthenticationPage {
    public SelenideElement userNameTextbox = $(byId("username"));

    public SelenideElement passwordTextbox = $(byId("password"));

    public SelenideElement loginButton = $(byName("login"));

    public SelenideElement userDataBox = $(byClassName("user_data_box"));

    public SelenideElement logInAndOutButton = $(byClassName("login-button"));

    public AuthenticationPage insertUsername(String username){
        userNameTextbox.click();
        userNameTextbox.setValue(username);
        return this;
    }

    public AuthenticationPage insertPassword(String password){
        passwordTextbox.click();
        passwordTextbox.setValue(password);
        return this;
    }

    public AuthenticationPage loginWithDefaultUser(){
        insertUsername("kasutajanimi");
        insertPassword("parool");
        loginButton.click();
        userDataBox.should(Condition.matchesText("Tere tulemast"));
        return this;
    }

}
