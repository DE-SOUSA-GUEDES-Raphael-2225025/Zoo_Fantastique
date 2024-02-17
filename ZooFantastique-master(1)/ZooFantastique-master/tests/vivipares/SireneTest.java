package vivipares;
import ZooFantastique.models.Age;
import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.creatures.vivipares.Sirene;
import ZooFantastique.models.enclos.Enclos;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class SireneTest {

    private Sirene sirene;
    private Enclos enclos;

    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des sirenes");
        sirene = new Sirene(enclos);
    }

    @Test
    public void testGetEnclos(){
        assertSame(enclos, sirene.getEnclos());
    }

    @Test
    public void testGetNom(){
        assertSame("Sirene", sirene.getNom());
    }

    @Test
    public void testGetSexe(){
        sirene.setSexe(Sexe.MALE);
        assertSame(Sexe.MALE, sirene.getSexe());
    }

    @Test
    public void testGetEtat() {
        sirene.setEtat(Etat.MALADE);
        assertSame(Etat.MALADE, sirene.getEtat());
    }

    @Test
    public void testGetAge() {
        sirene.setAge(Age.ADULTE);
        assertSame(Age.ADULTE, sirene.getAge());
    }

    @Test
    public void testGetSonEmit() {
        assertSame("Swish-swash!", sirene.getSonEmit());
    }

    @Test
    public void testGetSante() {
        assertEquals(10.0, sirene.getSante());
    }


    @Test
    public void testGetNomEspece() {
        assertEquals("Sirene", sirene.getNomEspece());
    }

    @Test
    public void testSetEnclos() {
        Enclos autreEnclos = new Enclos("test");
        sirene.setEnclos(autreEnclos);
        assertSame(autreEnclos, sirene.getEnclos());
    }

    @Test
    public void testSetHungry() {
        sirene.setHungry(true);
        assertTrue(sirene.isHungry());
    }

    @Test
    public void testIsHungry() {
        sirene.feed();
        assertFalse(sirene.isHungry());
    }

    @Test
    public void testIsDead() {
        sirene.setSante(0);
        assertTrue(sirene.isDead());
    }

}
