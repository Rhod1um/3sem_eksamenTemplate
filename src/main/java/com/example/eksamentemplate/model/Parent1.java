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
public class Parent1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent1id")
    private int parent1Id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "parent1", orphanRemoval = true) //orphanRemovel gør at foreign key ikke
    // forhindre at man fjerner parent når der er en child der refererer til den
    @JsonIgnore
    private Set<Child> children = new HashSet<>();
}
