package com.example.eksamentemplate.service;

import com.example.eksamentemplate.exception.ResourceNotFoundException;
import com.example.eksamentemplate.model.Parent2;
import com.example.eksamentemplate.repository.Parent2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Parent2Service {
    private final Parent2Repo parent2Repo;
    @Autowired
    public Parent2Service(Parent2Repo parent2Repo) {
        this.parent2Repo = parent2Repo;
    }
    //GET all
    public List<Parent2> getAll() {
        return parent2Repo.findAll();
    }
    //GET by id
    public ResponseEntity<Parent2> getById(Integer id) {
        if (parent2Repo.findById(id).isPresent()){
            return new ResponseEntity<>(parent2Repo.findById(id).get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent2 med id " + id + " blev ikke fundet");
        }
    }
    //POST
    public ResponseEntity<Parent2> create(Parent2 parent2) {
        //post er non-idempotent, så tjek om id findes i forvejen
        try {
            parent2Repo.save(parent2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //kan smide sin egen her
        } //man får den præcise fejl i fejlbesked
        return new ResponseEntity<>(parent2Repo.save(parent2), HttpStatus.CREATED);
    }
    //PUT
    public ResponseEntity<Parent2> update(Parent2 updatedParent2) {
        //PUT er idempotent og skal derfor tjekke id, for at den opdatere en der allerede eksisterer
        int id = updatedParent2.getParen2Id();
        if (parent2Repo.findById(id).isPresent()){
            return new ResponseEntity<>(parent2Repo.save(updatedParent2), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent2 med id " + id + " blev ikke fundet");
        }
    }
    //DELETE
    public ResponseEntity<Parent2> delete(Integer id) {
        if (parent2Repo.findById(id).isPresent()){
            Parent2 parent2 = parent2Repo.findById(id).get();
            parent2Repo.deleteById(id);
            return new ResponseEntity<>(parent2, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent2 med id " + id + " blev ikke fundet");
        }
    }
}
