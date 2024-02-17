package ZooFantastique.models.creatures.vivipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.IRevive;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.util.Random;

/**
 * La classe {@code Nymphe} représente une créature fantastique de type vivipare
 * qui a la capacité d'être ressuscitée. Elle étend les fonctionnalités de la classe
 * {@link Vivipare} et implémente l'interface {@link IRevive}.
 * <p>
 * La Nymphe peut avoir un processus de mise bas particulier et possède la capacité
 * d'être ressuscitée par une méthode spécifique.
 * </p>
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see Vivipare
 * @see IRevive
 */
public class Nymphe extends Vivipare implements IRevive {

    public Nymphe(Enclos enclos) {
        super("Nymphe", enclos, "Sssshhh...");
        this.setTaille(1.7);
        this.setPoids(new Random().nextInt(60,100));
    }

    public void revive() {
        super.setSante(getSanteMax());
        setEtat(Etat.PLEINE_FORME);
        setAge(Age.JEUNE);
        Notifications.create().title("Resurrection").text("La nymphe ressucite").showInformation();
    }

    @Override
    public void die(){
        this.revive();
    }
}
