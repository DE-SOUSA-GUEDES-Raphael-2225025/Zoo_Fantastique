package ZooFantastique.models.creatures.vivipares;

import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.CreatureFactory;
import ZooFantastique.models.enclos.Enclos;

import java.util.ArrayList;

public abstract class Vivipare extends Creature {

    private ArrayList<Vivipare> listBabies = new ArrayList<>();

    public Vivipare(String nom, Enclos enclos, String sonEmit) {
        super(nom, enclos, sonEmit);
    }

    public Vivipare(String nom, String sonEmit) {
        super(nom, sonEmit);
    }

    public void giveBirth(){
        Creature baby = new CreatureFactory().createCreature(this.getNom(), this.getEnclos());
    }

}
