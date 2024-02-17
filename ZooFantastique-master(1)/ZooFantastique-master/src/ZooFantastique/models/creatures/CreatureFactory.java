package ZooFantastique.models.creatures;

import ZooFantastique.models.creatures.ovipares.Dragon;
import ZooFantastique.models.creatures.ovipares.Kraken;
import ZooFantastique.models.creatures.ovipares.Megalodon;
import ZooFantastique.models.creatures.ovipares.Phoenix;
import ZooFantastique.models.creatures.vivipares.Licorne;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.Nymphe;
import ZooFantastique.models.creatures.vivipares.Sirene;
import ZooFantastique.models.enclos.Enclos;

public class CreatureFactory {

    public Creature createCreature(String nom, Enclos enclos){
        switch (nom){
            case "Dragon":
                return new Dragon(enclos);
            case "Kraken":
                return new Kraken(enclos);
            case "Megalodon":
                return new Megalodon(enclos);
            case "Phoenix":
                return new Phoenix(enclos);
            case "Licorne":
                return new Licorne(enclos);
            case "Lycanthrope":
                return new Lycanthrope(enclos);
            case "Nymphe":
                return new Nymphe(enclos);
            case "Sirene":
                return new Sirene(enclos);
            default:
                return null;

        }
    }
}
