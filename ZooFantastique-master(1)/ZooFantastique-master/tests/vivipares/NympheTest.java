package vivipares;
import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.vivipares.Nymphe;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class NympheTest {

    private Nymphe nymphe;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des nymphes");
        nymphe = new Nymphe(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, nymphe.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Nymphe", nymphe.getNom());
    }

    @Test
    public void testGetSexe(){
        nymphe.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, nymphe.getSexe());
    }

    @Test
    public void testGetEtat() {
        nymphe.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, nymphe.getEtat());
    }

    @Test
    public void testGetAge() {
        nymphe.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, nymphe.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Sssshhh...", nymphe.getSonEmit());
    }


    @Test
    public void testGetSante() {
        assertEquals(10.0, nymphe.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Nymphe", nymphe.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        nymphe.setEnclos(autreEnclos);
        assertSame(autreEnclos, nymphe.getEnclos());
    }

    @Test
    public void testSetHungry() {
        nymphe.setHungry(true);
        assertTrue(nymphe.isHungry());
    }

    @Test
    public void testIsHungry() {
        nymphe.feed();
        assertFalse(nymphe.isHungry());
    }

}
