package uiTests.tests.authentication;

import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import uiTests.pageObjects.AuthenticationPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthenticationTests {
    AuthenticationPage authenticationPage = new AuthenticationPage();

    @Before
    public void goToPageAndLogIn() {
        open("https://tellihorticom.koik.ee/");
        authenticationPage.userNameTextbox.waitUntil(Condition.exist, 8000);
        authenticationPage.loginWithDefaultUser();
    }

    @Test
    public void logOutTest(){
        authenticationPage.logInAndOutButton.should(Condition.matchesText("Logi Välja")).click();
        authenticationPage.logInAndOutButton.shouldNot(Condition.matchesText("Logi Välja"));
    }
}
