package models;
import org.junit.Rule;

import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();//checkout database rule uses


    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = setUpNewSighting();
        assertTrue(testSighting instanceof Sighting);
    }

    @Test
    public void getRangerName_returnsRangerNameInstance_true() {
        Sighting testSighting = setUpNewSighting();
        assertEquals("Morris",testSighting.getRangerName());
    }

    @Test
    public void getLocation_returnsLocationOnClassInitialization_true() {
        Sighting testSighting = setUpNewSighting();
        assertEquals("Zone A",testSighting.getLocation());
    }

    @Test
    public void save_insertsObjectsToTheDatabase_Sighting() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));

    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting firstSighting = setUpNewSighting();
        firstSighting.save();
        Sighting secondSighting = new Sighting("Ken","Zone B",2,"common");
        secondSighting.save();
        assertEquals(true,Sighting.all().get(0).equals(firstSighting));
        assertEquals(true,Sighting.all().get(1).equals(secondSighting));

    }

    @Test
    public void save_assignsIdToTheObject() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(),savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithTheSameId_thirdSighting() {
        Sighting firstSighting = setUpNewSighting();
        firstSighting.save();
        Sighting secondSighting = new Sighting("Mavin","Kiambu",2,"common");
        secondSighting.save();
        Sighting thirdSighting = new Sighting("Kennedy","Mashinani",2,"common");
        thirdSighting.save();
        assertEquals(Sighting.find(thirdSighting.getId()),thirdSighting);
    }
    @Test
    public void save_savesAnimalIdIntoDB_true() {
        Animals testAnimal = new Animals("Baboon");
        testAnimal.save();
        Sighting testSighting = new Sighting("Man","Zone C",testAnimal.getId(),"common");
        testSighting.save();
        Sighting savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getAnimalId(),testAnimal.getId());
    }

    public Sighting setUpNewSighting(){
        return new Sighting("Morris","Zone A",1,"common");
    }


}