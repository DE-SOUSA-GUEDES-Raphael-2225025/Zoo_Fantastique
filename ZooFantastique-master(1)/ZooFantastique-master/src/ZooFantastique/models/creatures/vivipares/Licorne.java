package ZooFantastique.models.creatures.vivipares;

import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.IRun;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.Random;

/**
 * La classe {@code Licorne} représente une créature fantastique de type vivipare
 * qui a la capacité de courir. Elle étend les fonctionnalités de la classe
 * {@link Vivipare} et implémente l'interface {@link IRun}.
 * <p>
 * La Licorne peut mettre bas et possède une grande agilité pour courir.
 * </p>
 * <p>
 *
 * @author [Raphael]
 * @version 1.0
 * @see Vivipare
 * @see IRun
 */
public class Licorne extends Vivipare implements IRun {

    public Licorne(Enclos enclos) {
        super("Licorne", enclos,"Huuuuu!");
        this.setTaille(new Random().nextInt(1,2));
        this.setPoids(new Random().nextInt(300,700));
    }


    /**
     * Permet à la Licorne de courir avec agilité.
     */
    @Override
    public void run() {
        Notifications.create().title("Court").text("La Licorne court").position(Pos.TOP_CENTER).showInformation();
    }
}