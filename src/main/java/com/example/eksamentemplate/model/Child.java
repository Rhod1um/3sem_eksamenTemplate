package com.example.eksamentemplate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "childid")
    private int childId;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent1id", referencedColumnName = "parent1id")
    //TODO: er det dårlig stil at have samme navn?
    //TODO: jeg kan ikke få det til at virke hvis referencedColumnName ikke er JPA navn frem for tabelnavn
    //name: navn til productowner tabel, referenced er navn i product tabel
    private Parent1 parent1;

    @ManyToOne
    @JoinColumn(name = "parent2id", referencedColumnName = "parent2id")
    private Parent2 parent2;
}
