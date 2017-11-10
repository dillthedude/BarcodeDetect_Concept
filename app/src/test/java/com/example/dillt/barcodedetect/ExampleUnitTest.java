package com.example.dillt.barcodedetect;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void createdItemShouldHaveABarCode() {
        assertNotNull(Item.getBarNumber());
    }

    @Test
    public void createdItemShouldHaveAName() {

    }
    @Test
    public void createdItemShouldHaveANumber() {

    }
}