package com.example.demo.controllers;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo.models.Chiste;
import com.example.demo.models.Person;
import com.example.demo.services.ChistesService;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejericio {
    @Autowired
    RickAndMortyService rickAndMortyService;

    @Autowired
    ChistesService chistesService;

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

    @DeleteMapping("/removeFile")
    public String removeFile(){
        boolean result = Utils.remove("datos.txt");
        return result ? "borrado correcto" : "no se puede borrar";
    }  
    
    @GetMapping("/RickandMorty/random")
    public String getRyMortyRandCharacter(){
        Person c = rickAndMortyService.getCharacterFromAPI();
        //Devolvemos una etiqueta img con la ruta de la imagen
        return "<img src = '"+c.image+"'/>" ;
      
    }

    //http://localhost:8080/rickandmorty/list
    @GetMapping("/rickandmorty/list")
    public String getRickAndMortyRandomList(){
        String web = "<h1>Lista de personas</h1>";
        ArrayList<Person> persons = rickAndMortyService.getCharactersFromAPI();
        for(Person person : persons){
            web+="<img src='"+person.image+"'/>";
        }
        return web;
    }

  
    //http://localhost:8080/listarChiste
    @GetMapping("/listarChiste")
    public String jokeList(){
        ArrayList<Chiste> jokes = chistesService.getAllChistes();
        String listado = "";
        for(Chiste joke: jokes){
            listado += joke.getText();
            
            listado += "<br/>";
        }
        return listado;

    }


    @PostMapping("/insertarchiste")
   public String addJoke(@RequestParam Map<String, String> body){
        String jokeText =  body.get("text");
        jokeText.replaceAll("<", "");
        jokeText.replaceAll(">", "");
        Chiste joke = new Chiste();
        joke.setText(jokeText);
        chistesService.saveChiste(joke);
        return "Chiste creado correctamente";
   }
 }



