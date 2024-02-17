package vivipares.lycanthrope;

import ZooFantastique.models.Sexe;
import ZooFantastique.models.creatures.ovipares.Dragon;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import ZooFantastique.models.creatures.vivipares.lycanthrope.RangDomination;
import ZooFantastique.models.enclos.Enclos;
import junit.framework.Assert;
import org.junit.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LycanthropeTest {

    private Lycanthrope lycanMale;
    private Lycanthrope lycanFemelle;
    private Lycanthrope lycan3;
    private Meute meute;
    private Enclos enclos;
    private Enclos autreEnclos;
    @Before
    public void setUp() {
        enclos = new Enclos("Enclos des Lycanthropes");
        autreEnclos = new Enclos("Enclos des Lycanthropes rivaux");
        lycanMale = new Lycanthrope(enclos, Sexe.MALE);
        lycanFemelle = new Lycanthrope(enclos, Sexe.FEMELLE);
        meute = new Meute(lycanMale, lycanFemelle);
        lycan3 = new Lycanthrope(enclos);
        lycanMale.initStats();
        lycanFemelle.initStats();
        lycan3.initStats();
    }

    @Test
    public void testRejoindreMeute(){
        lycan3.rejoindreMeute(meute);
        assertTrue(meute.getMembres().contains(lycan3));
    }

    @Test
    public void testQuitterMeute() {
        lycan3.rejoindreMeute(meute);
        lycan3.quitterMeute();
        assertFalse(meute.getMembres().contains(lycan3));
    }


}
