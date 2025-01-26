package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.DriverSetup;

public class PageTitleTest extends DriverSetup {

    @Test
    public void testTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Practice Software Testing";
        // assertEquals(expectedTitle, actualTitle);
        assertTrue(actualTitle.contains(expectedTitle));
    }
}
