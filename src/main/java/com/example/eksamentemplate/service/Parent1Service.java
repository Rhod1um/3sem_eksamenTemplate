package com.example.eksamentemplate.service;

import com.example.eksamentemplate.exception.ResourceNotFoundException;
import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.repository.Parent1Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class Parent1Service {
    private final Parent1Repo parent1Repo;
    @Autowired
    public Parent1Service(Parent1Repo parent1Repo) {
        this.parent1Repo = parent1Repo;
    }
    //GET all
    public List<Parent1> getAll() {
        return parent1Repo.findAll();
    }
    //GET by id
    public ResponseEntity<Parent1> getById(Integer id) {
        if (parent1Repo.findById(id).isPresent()){
            return new ResponseEntity<>(parent1Repo.findById(id).get(), HttpStatus.OK);
        } else {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Parent1 med id " + id + " blev ikke fundet");
        }
    }
    //POST
    public ResponseEntity<Parent1> create(Parent1 parent1) {
        //post er non-idempotent

        //post er non-idempotent, så tjek om id findes i forvejen
        //hvis det her var skolesystem, så må der maks være 20 children som en parent peger på
        //student er parent og course er child
        //så der må maks være 20 studerende som et course peger på. Course's student liste må ikke have mere
        //end 20 på sig. Child's parentliste må maks have 20
        //kan bruge en COUNT?
        //her tjek hvor mange parent1 child har i sin liste
        //nej det er parents som jo har lister af child
        //course er parent og student er child
        //så tjek parent liste

            try {
                parent1Repo.save(parent1);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //kan smide sin egen her
            } //man får den præcise fejl i fejlbesked
            return new ResponseEntity<>(parent1Repo.save(parent1), HttpStatus.CREATED); //sker hvis den ikke catcher
    }
    //PUT
    /*public ResponseEntity<Parent1> update(Integer id, @RequestBody Parent1 updatedParent1) {
        //PUT er idempotent og skal derfor tjekke id, for at den opdatere en der allerede eksisterer
        if (parent1Repo.findById(id).isPresent()){
            return new ResponseEntity<>(parent1Repo.save(updatedParent1), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent1 med id " + id + " blev ikke fundet");
        }
    }*/
    //PUT
    public ResponseEntity<Parent1> update(@RequestBody Parent1 updatedParent1) {
        //PUT er idempotent og skal derfor tjekke id, for at den opdatere en der allerede eksisterer
        //int id = updatedParent1.getParent1Id(); tjekkes direkte i kaldet nedenunder
        //da den ellers stopper før if'en hvis id'et ikke er der
        int id = updatedParent1.getParent1Id();
        if (parent1Repo.findById(id).isPresent()){
            return new ResponseEntity<>(parent1Repo.save(updatedParent1), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent1 med id " + id + " blev ikke fundet");
        }
    }
    //DELETE
    public ResponseEntity<Parent1> delete(Integer id) {
        if (parent1Repo.findById(id).isPresent()){
            Parent1 parent1 = parent1Repo.findById(id).get();
            parent1Repo.deleteById(id);
            return new ResponseEntity<>(parent1, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Parent1 med id " + id + " blev ikke fundet");
        }
    }

    //ekstra
    public ResponseEntity<Parent1> findByName(String name){
        if (parent1Repo.findByName(name).isPresent()){
            return new ResponseEntity<>(parent1Repo.findByName(name).get(), HttpStatus.OK);
        } else {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Parent1 med navn " + name + " blev ikke fundet");
        }
    }
}
