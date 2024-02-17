package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.ISwim;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.Random;

/**
 * La classe {@code Kraken} représente une créature fantastique de type ovipare
 * qui possède la capacité de nager. Elle étend les fonctionnalités de la classe
 * {@link Ovipare} et implémente l'interface {@link ISwim}.
 * <p>
 * Le Kraken peut pondre des œufs et nager.
 * </p>
 * <p>
 ISwim
 */
public class Kraken extends Ovipare implements ISwim {

    public Kraken(Enclos enclos) {
        super("Kraken",enclos,"Swooosh!");
        this.setTaille(new Random().nextInt(3,10));
        this.setPoids(new Random().nextInt(300,1100));
    }

    /**
     * Permet au Kraken de nager.
     */
    @Override
    public void swim() {
        Notifications.create().title("Nage").text("Le Kraken nage").position(Pos.TOP_CENTER).showInformation();
    }
}