package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.DriverSetup;

public class SignInTest extends DriverSetup {

    @Test
    public void testSignIn() {
        homePage.clickSignInBtn();
        assertTrue(signInPage.isDisplayed());
    }
}
