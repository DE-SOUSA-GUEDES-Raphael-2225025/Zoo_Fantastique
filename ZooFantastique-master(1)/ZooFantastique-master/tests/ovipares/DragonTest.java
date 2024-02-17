package ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.ovipares.Dragon;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class DragonTest {

    private Dragon dragon;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des dragons");
        dragon = new Dragon(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, dragon.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Dragon", dragon.getNom());
    }

    @Test
    public void testGetSexe(){
        dragon.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, dragon.getSexe());
    }

    @Test
    public void testGetEtat() {
        dragon.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, dragon.getEtat());
    }

    @Test
    public void testGetAge() {
        dragon.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, dragon.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Raaaargh!", dragon.getSonEmit());
    }

    @Test
    public void testGetSante() {
        assertEquals(10.0, dragon.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Dragon", dragon.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        dragon.setEnclos(autreEnclos);
        assertSame(autreEnclos, dragon.getEnclos());
    }

    @Test
    public void testSetHungry() {
        dragon.setHungry(true);
        assertTrue(dragon.isHungry());
    }

    @Test
    public void testIsHungry() {
        dragon.feed();
        assertFalse(dragon.isHungry());
    }

}
