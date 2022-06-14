package com.sparta.ab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest 
{
    @Test
    @DisplayName("check that I can run a test")
    void simpleTest(){
        //  assertEquals(true, 5==5);
        Assertions.assertEquals(1,1);
    }
}
