package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.ISwim;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.Random;

/**
 * La classe {@code Megalodon} représente une créature fantastique de type ovipare
 * qui a la capacité de nager. Elle étend les fonctionnalités de la classe
 * {@link Ovipare} et implémente l'interface {@link ISwim}.
 * <p>
 * Le Megalodon peut pondre des œufs et est capable de nager.
 * </p>
 * <p>
 * @author [raphael]
 * @version 1.0
 * @see Ovipare
 * @see ISwim
 */
public class Megalodon extends Ovipare implements ISwim {

    public Megalodon(Enclos enclos) {
        super("Megalodon",enclos, "Grrrrrblub!");
        this.setTaille(new Random().nextInt(100,300));
        this.setPoids(new Random().nextInt(5000,30000));
    }

    /**
     * Permet au Megalodon de nager avec puissance.
     */
    @Override
    public void swim() {
        Notifications.create().title("Nage").text("Le Megalodon nage").position(Pos.TOP_CENTER).showInformation();
    }
}
