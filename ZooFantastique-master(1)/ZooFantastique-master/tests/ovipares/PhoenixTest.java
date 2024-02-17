package ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.ovipares.Phoenix;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PhoenixTest {

    private Phoenix phoenix;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des phoenix");
        phoenix = new Phoenix(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, phoenix.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Phoenix", phoenix.getNom());
    }

    @Test
    public void testGetSexe(){
        phoenix.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, phoenix.getSexe());
    }

    @Test
    public void testGetEtat() {
        phoenix.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, phoenix.getEtat());
    }

    @Test
    public void testGetAge() {
        phoenix.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, phoenix.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Cuit cuit!", phoenix.getSonEmit());
    }

    @Test
    public void testGetSante() {
        assertEquals(10.0, phoenix.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Phoenix", phoenix.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        phoenix.setEnclos(autreEnclos);
        assertSame(autreEnclos, phoenix.getEnclos());
    }

    @Test
    public void testSetHungry() {
        phoenix.setHungry(true);
        assertTrue(phoenix.isHungry());
    }

    @Test
    public void testIsHungry() {
        phoenix.feed();
        assertFalse(phoenix.isHungry());
    }

}
