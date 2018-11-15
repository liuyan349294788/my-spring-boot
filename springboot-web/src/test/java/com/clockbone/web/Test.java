package com.clockbone.web;

import com.clockbone.web.response.TestObj;
import org.springframework.beans.factory.annotation.Autowired;

public class Test extends AbstratApplicationBaseBootTest {

    @Autowired
    private TestObj testObj;

    @org.junit.Test
    public void test(){

        System.out.println("testsetsetsetset");
        System.out.println(testObj);
        System.out.println(testObj.getAge());
        System.out.println(testObj.getName());
        System.out.println(testObj.getList());
        System.out.println(testObj.getMap());
        System.out.println(testObj.getType().getAge());
        System.out.println(testObj.getType().getName());

    }
}
