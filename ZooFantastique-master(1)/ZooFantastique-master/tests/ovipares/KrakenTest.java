package ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.ovipares.Kraken; // Assurez-vous que le chemin d'importation est correct
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class KrakenTest {

    private Kraken kraken;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des krakens");
        kraken = new Kraken(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, kraken.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Kraken", kraken.getNom());
    }

    @Test
    public void testGetSexe(){
        kraken.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, kraken.getSexe());
    }

    @Test
    public void testGetEtat() {
        kraken.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, kraken.getEtat());
    }

    @Test
    public void testGetAge() {
        kraken.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, kraken.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Swooosh!", kraken.getSonEmit());
    }

    @Test
    public void testGetSante() {
        assertEquals(10.0, kraken.getSante());
    }

    @Test
    public void testGetNomEspece() {
        assertEquals("Kraken", kraken.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        kraken.setEnclos(autreEnclos);
        assertSame(autreEnclos, kraken.getEnclos());
    }

    @Test
    public void testSetHungry() {
        kraken.setHungry(true);
        assertTrue(kraken.isHungry());
    }

    @Test
    public void testIsHungry() {
        kraken.feed();
        assertFalse(kraken.isHungry());
    }

    @Test
    public void testIsDead() {
        kraken.setSante(0);
        assertTrue(kraken.isDead());
    }

}
