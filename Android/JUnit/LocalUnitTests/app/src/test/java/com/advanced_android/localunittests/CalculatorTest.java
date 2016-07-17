package com.advanced_android.localunittests;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by shoma2da on 2015/12/21.
 */
public class CalculatorTest {

    @Before
    public void setUp() {
        System.out.println("--- Start ---");
    }

    @After
    public void tearDown() {
        System.out.println("--- End ---");
    }


    @Test
    public void evaluatesExpression1() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

    @Ignore("unimplemented")
    @Test
    public void evaluatesExpression2() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("2+2+3");
        assertEquals(7, sum);
    }



}
