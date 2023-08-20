package com.example.eksamentemplate.config;

import com.example.eksamentemplate.model.Child;
import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.model.Parent2;
import com.example.eksamentemplate.service.ChildService;
import com.example.eksamentemplate.service.Parent1Service;
import com.example.eksamentemplate.service.Parent2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final ChildService childService;
    private final Parent1Service parent1Service;
    private final Parent2Service parent2Service;
    @Autowired
    public InitData(ChildService childService, Parent1Service parent1Service, Parent2Service parent2Service) {
        this.childService = childService;
        this.parent1Service = parent1Service;
        this.parent2Service = parent2Service;
    }

    @Override
    public void run(String... args) throws Exception {
        if (false) { //gøres så dette ikke gøres hver gang man køre programmet men nemt kan ændres

            Parent1 parent1 = new Parent1(); //Evt. lav constructor så er det hurtigere at lave objekter her
            parent1.setName("parent1");
            parent1Service.create(parent1);

            Parent2 parent2 = new Parent2();
            parent2.setName("parent2");
            parent2Service.create(parent2);

            Child child = new Child();
            child.setName("child1");
            child.setParent1(parent1);
            child.setParent2(parent2);
            childService.create(child);
        }
    }
}
