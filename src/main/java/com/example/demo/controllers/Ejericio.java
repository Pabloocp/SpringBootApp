package com.example.demo.controllers;



import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
