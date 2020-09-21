package com.cjs.javaConcurrencyLearning;


import java.util.*;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {

        String[] strings = new String[]{"abc", "abc2", "abc3"};

        Stream<String> stringStream = Arrays.stream(strings);

        String[] strings2 = stringStream.filter((obj)->{
            return !obj.equals("abc");
        }).toArray(String[]::new);



        for (String string : strings2) {
            System.out.println(string);
        }


    }
}
