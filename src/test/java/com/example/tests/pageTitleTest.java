package com.example.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.util.DriverSetup;

public class pageTitleTest extends DriverSetup {

    @Test
    public void testTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";
        assertEquals(expectedTitle, actualTitle);
    }
}
