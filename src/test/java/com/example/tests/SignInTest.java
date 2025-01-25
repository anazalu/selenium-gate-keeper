package com.example.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.util.DriverSetup;

public class SignInTest extends DriverSetup {

    @Test
    public void testTitle() {
        homePage.clickSignInBtn();
        assertTrue(true);
        assertFalse(false);
        assertTrue(signInPage.isDisplayed());
    }
}
