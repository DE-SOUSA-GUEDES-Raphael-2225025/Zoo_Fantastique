package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.vivipares.Vivipare;
import ZooFantastique.models.enclos.Enclos;

import java.util.ArrayList;

public abstract class Ovipare extends Creature {

    public Ovipare(String nom, Enclos enclos, String sonEmit) {
        super(nom, enclos, sonEmit);
    }

    public Ovipare(String nom, String sonEmit) {
        super(nom, sonEmit);
    }

    public Oeuf lay(){
        return new Oeuf("Oeuf",this.getEnclos(),"rrrrr",this);
    }
}
