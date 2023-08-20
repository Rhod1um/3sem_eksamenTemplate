package com.example.eksamentemplate.repository;

import com.example.eksamentemplate.model.Parent1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Parent1Repo extends JpaRepository<Parent1, Integer> {
    Optional<Parent1> findByName(String name);
    //Integer countChildren();
}
