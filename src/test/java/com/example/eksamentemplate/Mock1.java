package com.example.eksamentemplate;

import com.example.eksamentemplate.controller.Parent1Controller;
import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.repository.Parent1Repo;
import com.example.eksamentemplate.service.Parent1Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mock1 { //fjern public? behøves ikke

    @Autowired
    private Parent1Repo parent1Repo;

    @BeforeEach
    public void setup() {
        Parent1Service parent1Service = new Parent1Service(parent1Repo);
        Parent1Controller parent1Controller = new Parent1Controller(parent1Service);
    }

    @Test
    void testInlineMockMapWhen(){ //man laver when klasse
        Map<Integer, String> mapMock = mock(Map.class);
        when(mapMock.get(1085)).thenReturn("Roskilde");

        //mapMock.put(1085, "Roskilde");
        String str = mapMock.get(1085);
        System.out.println(str); //husk sout hvis man vil se hvad der kommer ud
        //assertTrue(mapMock.size()>0);
        Assertions.assertEquals("Roskilde", str);
    }
    @Test
    void testParent1FindByNameMock(){
        //lav mock:
        Parent1Repo parent1Repo = Mockito.mock(Parent1Repo.class);
        //gør klar:
        String name = "parent1ny";
        Parent1 parent1 = new Parent1();
        Optional<Parent1> optParent = Optional.of(parent1);
        parent1.setName(name);
        String expected = parent1.getName();

        //mocker repo adfærd, returnerer optional
        when(parent1Repo.findByName("parent1ny")).thenReturn(optParent);

        String actual = parent1Repo.findByName(name).get().getName();

        Assertions.assertEquals(expected, actual, "are the names equal");
    }
}
