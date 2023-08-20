package com.example.eksamentemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Parent2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent2id")
    private int Paren2Id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "parent2", orphanRemoval = true)
    @JsonIgnore
    private Set<Child> children = new HashSet<>();
}
