package com.example.demo.controllers;



import java.text.MessageFormat;

import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejericio {
    @GetMapping("/")
    public String greet(){
        return "Bienvenido al servidor backend";
    }

    @GetMapping("/aleatorio")
    public String Aleatorio(){
        long r = Math.round( Math.random()*100) ;
        return r + "";
    }

    @GetMapping("/palindromo/{name}")
    public String EsPalindromo(@PathVariable String name){
       boolean espalindromo = Utils.EsPalindromoBoolean(name);
       return espalindromo ? "Es palindromo" : "No es palindromo";
    }

    //via query

    @GetMapping("/sumar")
    public String add(@RequestParam String num1,@RequestParam String num2){
        int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
        Object params[] = {num1,num2,resultado};
        return MessageFormat.format("La suma de {0} y {1} es {2}", params );
    }


}
