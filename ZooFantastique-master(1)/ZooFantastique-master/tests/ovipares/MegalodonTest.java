package ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.ovipares.Megalodon;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class MegalodonTest {

    private Megalodon megalodon;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des megalodons");
        megalodon = new Megalodon(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, megalodon.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Megalodon", megalodon.getNom());
    }

    @Test
    public void testGetSexe(){
        megalodon.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, megalodon.getSexe());
    }

    @Test
    public void testGetEtat() {
        megalodon.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, megalodon.getEtat());
    }

    @Test
    public void testGetAge() {
        megalodon.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, megalodon.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Grrrrrblub!", megalodon.getSonEmit());
    }

    @Test
    public void testGetSante() {
        assertEquals(10.0, megalodon.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Megalodon", megalodon.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        megalodon.setEnclos(autreEnclos);
        assertSame(autreEnclos, megalodon.getEnclos());
    }

    @Test
    public void testSetHungry() {
        megalodon.setHungry(true);
        assertTrue(megalodon.isHungry());
    }

    @Test
    public void testIsHungry() {
        megalodon.feed();
        assertFalse(megalodon.isHungry());
    }

    @Test
    public void testIsDead() {
        megalodon.setSante(0);
        assertTrue(megalodon.isDead());
    }

}
