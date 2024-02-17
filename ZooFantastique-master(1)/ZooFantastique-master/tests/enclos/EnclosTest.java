package enclos;

import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.ovipares.Dragon;
import ZooFantastique.models.creatures.vivipares.Sirene;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import ZooFantastique.models.enclos.Aquarium;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.enclos.Proprete;
import Exceptions.EnclosFullException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class EnclosTest {

    private Enclos enclosSirene;
    private Enclos enclosDragon;
    private Enclos enclosLycanthrope;

    @Before
    public void setUp() {
        /* Initialisation d'un enclot qui peut contenir 10 sirènes max*/
        enclosSirene = new Enclos("Enclos des Sirènes");
        /* Initialisation d'un enclot qui peut contenir 20 dragons max*/
        enclosDragon = new Enclos("Enclos des Dragons", 20);
        enclosLycanthrope = new Enclos("Enclos des Lycanthropes");
    }

    @Test
    public void testGetNom() {
        assertEquals("Enclos des Sirènes", enclosSirene.getNom());
        assertEquals("Enclos des Dragons", enclosDragon.getNom());
    }

    @Test
    public void testGetSetMeute() {
        Lycanthrope male = new Lycanthrope(enclosLycanthrope, Sexe.MALE);
        Lycanthrope femelle = new Lycanthrope(enclosLycanthrope, Sexe.FEMELLE);
        Meute meute = new Meute(male, femelle);
        enclosLycanthrope.setMeute(meute);
        assertEquals(meute, enclosLycanthrope.getMeute());
    }

    @Test
    public void testClean() {
        enclosDragon.setPropreteDegre(Proprete.MAUVAIS);
        enclosDragon.clean();
        enclosSirene.setPropreteDegre(Proprete.CORRECT);
        enclosSirene.clean();
        assertEquals(Proprete.BON, enclosDragon.getPropreteDegre());
        assertEquals(Proprete.BON, enclosSirene.getPropreteDegre());
    }

    @Test
    public void testRemoveCreature() {
        Sirene siren1 = new Sirene(enclosSirene);
        Sirene siren2 = new Sirene(enclosSirene);
        Sirene siren3 = new Sirene(enclosSirene);
        enclosSirene.removeCreature(siren3);
        assertEquals(2, enclosSirene.getNbCreaturePresente());
    }
    @Test
    public void testfeedAllCreature() {
        Dragon dragon1 = new Dragon(enclosDragon);
        Dragon dragon2 = new Dragon(enclosDragon);
        Dragon dragon3 = new Dragon(enclosDragon);
        dragon1.setHungry(true);
        dragon2.setHungry(true);
        dragon3.setHungry(true);
        try{
            enclosDragon.addCreature(dragon1);
            enclosDragon.addCreature(dragon2);
            enclosDragon.addCreature(dragon3);
        } catch (EnclosFullException e ){
            e.printStackTrace();
        }
        enclosDragon.nourrirAllCreature();

        assertFalse(dragon1.isHungry());
    }

    @Test
    public void testSizeEnclos() {
        Dragon dragon = new Dragon(enclosDragon);
        assertEquals(1, enclosDragon.getNbCreaturePresente());
    }

    @Test
    public void testIsEmpty() {
        Sirene sirene = new Sirene(enclosSirene);
        assertTrue(enclosDragon.isEmpty());
        assertFalse(enclosSirene.isEmpty());
    }

    @Test
    public void testLowerProperty() {
        enclosDragon.lowerProperty();
        assertEquals(Proprete.CORRECT, enclosDragon.getPropreteDegre());
    }


}





