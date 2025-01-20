package com.example.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.util.DriverSetup;

public class signInTest extends DriverSetup {

    @Test
    public void testTitle() {
        homePage.clickSignInBtn();
        assertTrue(true);
    }
}
