package com.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Chiste;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ChisteRepository extends CrudRepository<Chiste,Long>{
    
}
