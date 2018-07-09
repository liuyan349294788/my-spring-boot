package com.clockbone.web;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args){
        streamTest();

    }

    public static void streamTest(){
        List<String> list1 = Arrays.asList("1","x","3","4");
        List<String> list2 = Arrays.asList("5","5","5","6","5");
        List<String> filterList = list1.stream().filter(e1->{
            Optional<String> o = list2.stream().filter(e2-> Objects.equals(e1,e2)).findFirst();
            return o.isPresent();
        }).collect(Collectors.toList());
        System.out.println(filterList);
    }
}
