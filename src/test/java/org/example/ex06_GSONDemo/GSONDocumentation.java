package org.example.ex06_GSONDemo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

public class GSONDocumentation {
   @Test
   public  void test_GSON(){
        Gson gson = new Gson();
        //gson.toJson(objectRef) -- Serialization(Java to JSON)
        //gson.fromJson(jsonString,ClassName.class) -- Deserialization(JSON to Java)
    }
}
