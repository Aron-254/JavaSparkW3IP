package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
public class endangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animals_instantiatesCorrectly_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        assertTrue(testEndangered instanceof endangeredAnimal);
    }

    @Test
    public void getName_animalsInstantiatesWithName_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        assertEquals("Baboon", testEndangered.getName());

    }

    @Test
    public void returnsTrueIfNamesAreTheSame_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        endangeredAnimal anotherEndangered = new endangeredAnimal("Baboon","healthy","adult");
        assertTrue(testEndangered.equals(anotherEndangered));
    }

    @Test
    public void save_insertsObjectIntoDatabase_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        testEndangered.save();
        System.out.println(testEndangered);
        assertTrue(endangeredAnimal.getAll().get(0).equals(testEndangered));
    }

    @Test
    public void getAll_returnsAllInstancesOfAnimals_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        testEndangered.save();
        endangeredAnimal secondEndangered = new endangeredAnimal("Lion","ill","newborn");
        secondEndangered.save();
        assertEquals(true,endangeredAnimal.getAll().get(0).equals(testEndangered));
        assertEquals(true, endangeredAnimal.getAll().get(1).equals(secondEndangered));
    }

    @Test
    public void save_assignsIdToObject_true() {
        endangeredAnimal testEndangered = setUpNewEndangered();
        testEndangered.save();
        endangeredAnimal secondEndangered = endangeredAnimal.getAll().get(0);
        assertEquals(testEndangered.getId(),secondEndangered.getId());

    }












    public endangeredAnimal setUpNewEndangered(){
        return new endangeredAnimal ("Baboon","ill","adult");
    }


}