package com.example.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.base.BaseTest;

public class InitialTest extends BaseTest {

    @Test
    public void testTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";
        assertEquals(expectedTitle, actualTitle);
    }
}
