package com.example.eksamentemplate.service;

import com.example.eksamentemplate.exception.ResourceNotFoundException;
import com.example.eksamentemplate.model.Child;
import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.repository.ChildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChildService {
    private final ChildRepo childRepo;
    @Autowired
    public ChildService(ChildRepo childRepo) {
        this.childRepo = childRepo;
    }
    //repo skal ikke annoteres med @Repository, autwire kan altid finde repo
    //GET all
    public List<Child> getAll() {
        return childRepo.findAll();
    }
    //GET by id
    public ResponseEntity<Child> getById(Integer id) {
        //String className = childRepo.findById(id).getClass().getSimpleName();

        if (childRepo.findById(id).isPresent()){
            return new ResponseEntity<>(childRepo.findById(id).get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Child med id " + id + "blev ikke fundet.");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //String className = this.getClass().getSimpleName();
        //className initialization er redundant?? Kan den finde class name ud fra className variabel alene?

        //kan ikke bruge det her orElseThrow med ResponseEntity
        //orElsThrow er på Optional
        //return childRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(className + " med id " + id));

    }
    //POST
    public ResponseEntity<Child> create(Child child) {
        //check hvor mange children parent1 har
        Parent1 parent1 = child.getParent1();
        System.out.println(parent1);
        Integer count = childRepo.countAllChildrenByParent1(parent1);
        System.out.println(count);
        if (count < 10) {
            System.out.println("den skal ikke gå videre");
            try {
                childRepo.save(child);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //kan smide sin egen her
            } //man får den præcise fejl i fejlbesked
            return new ResponseEntity<>(childRepo.save(child), HttpStatus.CREATED);
        } else throw new ResponseStatusException(HttpStatus.CONFLICT,
                "der er for mange children på denne parent1");
    }
    //PUT
    public ResponseEntity<Child> update(Child updatedChild) {
        //PUT er idempotent og skal derfor tjekke id, for at den opdatere en der allerede eksisterer
        int id = updatedChild.getChildId();
        if (childRepo.findById(id).isPresent()){
            return new ResponseEntity<>(childRepo.save(updatedChild), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Child med id " + id + "blev ikke fundet.");
        }
    }
    //DELETE
    public ResponseEntity<Child> delete(Integer id) {
        if (childRepo.findById(id).isPresent()){
            Child child = childRepo.findById(id).get();
            childRepo.deleteById(id);
            return new ResponseEntity<>(child, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Child med id " + id + "blev ikke fundet.");
        }
    }

    //Ekstra
    public ResponseEntity<Child> findByName(String name){
        if (childRepo.findByName(name).isPresent()){
            return new ResponseEntity<>(childRepo.findByName(name).get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Child med navn " + name + "blev ikke fundet.");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public List<Child> findByParent1Name(String name){
        return childRepo.findByParent1Name(name);
    }
}
