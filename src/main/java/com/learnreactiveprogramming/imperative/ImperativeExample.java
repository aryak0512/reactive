package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {

    public static void main(String[] args) {
        var namesList = List.of("alex", "aryak", "joe");
        List<String> namesGreaterThanSize = getNamesGreaterThanSize(namesList, 3);
        System.out.printf("names : " + namesGreaterThanSize);
    }

    private static List<String> getNamesGreaterThanSize(List<String> namesList, int size) {
        List<String> names = new ArrayList<>();
        for (String name : namesList){
            if(name.length() > size){
                names.add(name);
            }
        }
        return names;
    }
}
