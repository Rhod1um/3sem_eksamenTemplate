package com.example.eksamentemplate.controller;

import com.example.eksamentemplate.model.Child;
import com.example.eksamentemplate.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/child")
public class ChildPageController {

    private final ChildService childService;

    @Autowired
    public ChildPageController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping
    public List<Child> getAll(){
        return childService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Child> getOne(@PathVariable Integer id){
        return childService.getById(id);
    }
    @PostMapping
    public ResponseEntity<Child> create(@RequestBody Child child){
        return childService.create(child);
    }
    @PutMapping
    public ResponseEntity<Child> update(@RequestBody Child child){
        return childService.update(child);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Child> delete(@PathVariable Integer id){
        return childService.delete(id);
    }

    //Ekstra
    @GetMapping("/name/{name}") //skal have /name/ ellers er der ambiguous mapping med /id
    //intellij ved jo ikke om strengen er et navn eller id
    public ResponseEntity<Child> findByName(@PathVariable String name){
        return childService.findByName(name);
    }
    @GetMapping("/parent1name/{name}")
    public List<Child> findByParent1Name(@PathVariable String name){
        return childService.findByParent1Name(name);
    }
}
