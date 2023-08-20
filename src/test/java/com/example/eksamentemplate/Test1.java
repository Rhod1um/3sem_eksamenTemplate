package com.example.eksamentemplate;

import java.util.Arrays;

import com.example.eksamentemplate.controller.Parent1Controller;
import com.example.eksamentemplate.model.Parent1;
import com.example.eksamentemplate.repository.Parent1Repo;
import com.example.eksamentemplate.service.Parent1Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

//ingen annotering, behøver man ikke hvis det er simple tests. Så kan man dog heller ikke autowire
//kun annoter hvis man fx skal have hele spring startet.
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class Test1 { //fjern public foran class Test

    private Parent1Controller parent1Controller;
    @Autowired
    private Parent1Repo parent1Repo;

    @BeforeEach
    public void setup() {
        Parent1Service parent1Service = new Parent1Service(parent1Repo);
        parent1Controller = new Parent1Controller(parent1Service);
    }

    @Test
    public void testarr() {
        //Arrange - klargør objekter og whatnot
        int[] numbers = {1, 2, 3, 8, 4};
        int[] expected = {1, 2, 3, 4, 8};

        //Act - udfør den kode der skal testes
        Arrays.sort(numbers);

        //Assertions.assertEqual(expected, numbers);
        //assertEquals funktion ændre sig, der står version når man tager den ind
        //metode kalder == metode, pointer er ikke ens
        //derfr bruges denne:
        //Assert - se om det er hvad man forventer
        assertArrayEquals(expected, numbers, "Tester 5 tal ");
    }

    @Test
    public void testParent1FindByName() {
        //testen fejler ved at sammenligne hele objekter fordi det er to forskellige instantieringer
        //af samme row hentet fra databasen. Det første laver vi selv og det andet instantieres
        //når det hentes ud af databasen, automatisk af spring. Derfor skal noget andet sammenlignes såsom id/navn

        //her sammenlignes id'er

        //Arrange - klargør objekter og whatnot
        String name = "parent1ny";
        Parent1 parent1 = new Parent1();
        parent1.setName(name);
        String expected = parent1.getName();
        if (parent1Repo.findByName(name).isPresent()){
            int id = parent1Repo.findByName(name).get().getParent1Id();
            parent1Controller.delete(id);
        }

        parent1Repo.save(parent1);
        int id = parent1Repo.findByName(name).get().getParent1Id();
        System.out.println(id);

        //Act - udfør den kode der skal testes
        String actual = parent1Repo.findById(id).get().getName();

        //Assert - se om det er hvad man forventer
        Assertions.assertEquals(actual, expected, "Er id'erne ens");
    }
}
