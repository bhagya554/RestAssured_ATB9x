package org.example.ex03_TestNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameters {
    @Parameters({"browser","url"})
    @Test
    public void openURL(String browserName,String siteName){
        if(browserName.equals("chrome")){
            System.out.println("Chrome");
        } else if (browserName.equals("firefox")) {
            System.out.println("Firefox");
        }
        System.out.println(siteName);
    }
}
