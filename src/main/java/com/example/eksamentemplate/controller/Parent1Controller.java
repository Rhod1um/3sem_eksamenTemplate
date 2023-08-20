package com.example.eksamentemplate.controller;

import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.service.Parent1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "Authorization"})
@RestControllerAdvice
@RequestMapping("/parent1")
public class Parent1Controller {
    private final Parent1Service parent1Service;

    @Autowired
    public Parent1Controller(Parent1Service parent1Service) {
        this.parent1Service = parent1Service;
    }

    @GetMapping //med og uden slash til sidst i url er ikke længere det samme i spring boot
    public List<Parent1> getAll(){
        return parent1Service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Parent1> getOne(@PathVariable Integer id){
        return parent1Service.getById(id);
    }
    @PostMapping
    public ResponseEntity<Parent1> create(@RequestBody Parent1 parent1){
        return parent1Service.create(parent1);
    }
    @PutMapping
    public ResponseEntity<Parent1> update(@RequestBody Parent1 parent1){
        return parent1Service.update(parent1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Parent1> delete(@PathVariable Integer id){
        return parent1Service.delete(id);
    }

    //ekstra
    @GetMapping("/name/{name}")
    public ResponseEntity<Parent1> findByName(@PathVariable String name){
        return parent1Service.findByName(name);
    }
}
