package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Chiste;
import com.repositories.ChisteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChistesService {
    @Autowired
    ChisteRepository chisteRepository;
    public Chiste saveChiste(Chiste chiste){
         // INSERT INTO chistes (text) values()
        return chisteRepository.save(chiste);
    }

    public ArrayList<Chiste> getAllChistes(){
        // Select * from chistes
        ArrayList<Chiste> chistes =  (ArrayList<Chiste>) chisteRepository.findAll();
        return chistes ;
    }
}
