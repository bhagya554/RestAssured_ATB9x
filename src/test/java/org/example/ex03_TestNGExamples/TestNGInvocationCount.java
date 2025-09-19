package org.example.ex03_TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGInvocationCount {
    @Test(invocationCount = 3)
    public void test01(){
        Assert.assertTrue(true);
    }
}
