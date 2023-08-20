package com.example.eksamentemplate.repository;

import com.example.eksamentemplate.model.Child;
import com.example.eksamentemplate.model.Parent1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepo extends JpaRepository<Child, Integer> {

    //de her mere specifikke metoder lægges i child-klassen fordi her kan man finde den ud
    //fra parent. Omvendt kan man ikke finde parent ud fra child, da parent ikke har reference til child i db

    //enkelt objekt returneres som optional og ellers bare liste
    //Optional<Child> findByNavn(String navn);
    //Optional<Child> findKommuneByNavn(String navn);
    //List<Child> findKommuneByRegionKode(String kode);
    Optional<Child> findByName(String name);
    List<Child> findByParent1Name(String name);
    Integer countAllChildrenByParent1(Parent1 parent1);
}
