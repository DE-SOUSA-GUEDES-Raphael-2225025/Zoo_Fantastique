package vivipares;
import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.vivipares.Licorne;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LicorneTest {

    private Licorne licorne;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des licornes");
        licorne = new Licorne(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, licorne.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Licorne", licorne.getNom());
    }

    @Test
    public void testGetSexe(){
        licorne.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, licorne.getSexe());
    }

    @Test
    public void testGetEtat() {
        licorne.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, licorne.getEtat());
    }

    @Test
    public void testGetAge() {
        licorne.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, licorne.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Huuuuu!", licorne.getSonEmit());
    }


    @Test
    public void testGetSante() {
        assertEquals(10.0, licorne.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Licorne", licorne.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        licorne.setEnclos(autreEnclos);
        assertSame(autreEnclos, licorne.getEnclos());
    }

    @Test
    public void testSetHungry() {
        licorne.setHungry(true);
        assertTrue(licorne.isHungry());
    }

    @Test
    public void testIsHungry() {
        licorne.feed();
        assertFalse(licorne.isHungry());
    }

    @Test
    public void testIsDead() {
        licorne.setSante(0);
        assertTrue(licorne.isDead());
    }

}
