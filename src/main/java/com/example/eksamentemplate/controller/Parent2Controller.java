package com.example.eksamentemplate.controller;

import com.example.eksamentemplate.model.Parent2;
import com.example.eksamentemplate.service.Parent2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/parent2")
public class Parent2Controller {
    private final Parent2Service parent2Service;

    @Autowired
    public Parent2Controller(Parent2Service parent2Service) {
        this.parent2Service = parent2Service;
    }

    @GetMapping
    public List<Parent2> getAll(){
        return parent2Service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Parent2> getOne(@PathVariable Integer id){
        return parent2Service.getById(id);
    }
    @PostMapping
    public ResponseEntity<Parent2> create(@RequestBody Parent2 parent2){
        return parent2Service.create(parent2);
    }
    @PutMapping
    public ResponseEntity<Parent2> update(@RequestBody Parent2 parent2){
        return parent2Service.update(parent2);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Parent2> delete(@PathVariable Integer id){
        return parent2Service.delete(id);
    }
}
