package com.example.demo.utils;

public class Utils {
    public static boolean EsPalindromoBoolean(String name){
        StringBuilder builder = new StringBuilder(name);
        String reverso = builder.reverse().toString();
        boolean espalindromo = name.equals(reverso);
        return espalindromo;
     }
}
