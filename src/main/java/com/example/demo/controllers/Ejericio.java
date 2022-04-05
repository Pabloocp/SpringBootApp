package com.example.demo.controllers;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Map;

import javax.print.event.PrintEvent;

import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //mediante mapas o diccionarios
    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam Map<String,String> body){
        String ArticleValue = body.get("article");
        String priceValue = body.get("price");
        
        //valido si no son elementos vacios
        if(ArticleValue.equals("") || priceValue.equals("")){
            return "Error,datos incorrectos";
      
        }
        // precio negativo
        if(Integer.valueOf(priceValue) < 0){
            return "Precio negativo";
        }
    
        //Guardamos en disco
        try {
            Utils.save("datos.txt", ArticleValue+ "," + priceValue + "\n");
        } catch (Exception e) {
            return "error al guardar disco";
        }
       // Devolver msg cliente
       return "Producto guardado correctamente";
       
    }

        
        
 }



