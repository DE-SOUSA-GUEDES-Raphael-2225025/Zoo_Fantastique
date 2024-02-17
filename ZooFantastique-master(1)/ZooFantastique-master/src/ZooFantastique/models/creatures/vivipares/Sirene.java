package ZooFantastique.models.creatures.vivipares;

import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.ISwim;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.Random;

/**
 * La classe {@code Sirene} représente une créature fantastique de type vivipare
 * qui a la capacité de nager. Elle étend les fonctionnalités de la classe
 * {@link Vivipare} et implémente l'interface {@link ISwim}.
 * <p>
 * La Sirène peut avoir un processus de mise bas particulier et possède la capacité
 * de nager avec grâce.
 * </p>
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see Vivipare
 * @see ISwim
 */
public class Sirene extends Vivipare implements ISwim {

    public Sirene(Enclos enclos) {
        super("Sirene", enclos, "Swish-swash!");
        this.setTaille(0.6);
        this.setPoids(new Random().nextInt(30, 70));
    }


    /**
     * Permet à la Sirène de nager avec grâce.
     */
    @Override
    public void swim() {
        Notifications.create().title("Nage").text("La Sirene court").position(Pos.TOP_CENTER).showInformation();
    }
}

