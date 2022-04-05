package com.example.demo.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
    public static boolean EsPalindromoBoolean(String name){
        StringBuilder builder = new StringBuilder(name);
        String reverso = builder.reverse().toString();
        boolean espalindromo = name.equals(reverso);
        return espalindromo;
     }

    public static void save(String filename,String texto) throws IOException{
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(filename,true); //para  no sobreescriba
            pw = new PrintWriter(fw);
            pw.print(texto);
            
       }finally{ //siempre se ejecuta,sin importar fallos
            if(pw!=null)  pw.close();
        }
    }

}
