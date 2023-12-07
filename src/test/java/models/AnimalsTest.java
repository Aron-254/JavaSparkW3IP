package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {


    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animals_instantiatesCorrectly_true() {
        Animals testAnimal = setUpNewAnimal();
        assertTrue(testAnimal instanceof Animals);
    }

    @Test
    public void getName_animalsInstantiatesWithName_true() {
        Animals testAnimal = setUpNewAnimal();
        assertEquals("Baboon", testAnimal.getName());

    }

    @Test
    public void returnsTrueIfNamesAreTheSame_true() {
        Animals testAnimal = setUpNewAnimal();
        Animals anotherAnimal = new Animals("Baboon");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_insertsObjectIntoDatabase_true() {
        Animals testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertTrue(Animals.all().get(0).equals(testAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimals_true() {
        Animals testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animals secondAnimal = new Animals("Lion");
        secondAnimal.save();
        assertEquals(true,Animals.all().get(0).equals(testAnimal));
        assertEquals(true, Animals.all().get(1).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject_true() {
        Animals testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animals savedAnimal = Animals.all().get(0);
        assertEquals(testAnimal.getId(),savedAnimal.getId());

    }

    @Test
    public void find_returnsAnimalWithSameId() {
        Animals testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animals anotherAnimal = new Animals("Dog");
        anotherAnimal.save();
        assertEquals(Animals.find(anotherAnimal.getId()),anotherAnimal);
    }

    @Test
    public void animalSavesWithTypeCommon(){
        Animals animal = setUpNewAnimal();
        animal.save();
        assertEquals("Common", Animals.all().get(0).getType());
    }


    public Animals setUpNewAnimal(){
        return new Animals ("Baboon");
    }

}