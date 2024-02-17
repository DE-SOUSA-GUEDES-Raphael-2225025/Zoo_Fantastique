package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.IFly;
import ZooFantastique.models.interfaces.IRevive;
import ZooFantastique.models.interfaces.IRun;
import ZooFantastique.models.interfaces.ISwim;
import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import utils.NotificationUtils;

import java.util.Random;

/**
 * La classe {@code Dragon} représente une créature fantastique qui étend les
 * fonctionnalités de la classe {@link Ovipare} et implémente plusieurs interfaces
 * pour définir ses capacités spécifiques.
 * <p>
 * Le dragon peut pondre des œufs, voler, courir, nager et être ressuscité par magie.
 * </p>
 * <p>
 *
 * @author [Rapahel]
 * @version 1.0
 * @see Ovipare
 * @see IRun
 * @see ISwim
 * @see IFly
 * @see IRevive
 */
public class Dragon extends Ovipare implements IRun, ISwim, IFly, IRevive {


    public Dragon(Enclos enclos) {
        super("Dragon", enclos, "Raaaargh!");
        this.setTaille(new Random().nextInt(10,50));
        this.setPoids(new Random().nextInt(1000,10000));
    }


    @Override
    public void fly() {
        Notifications.create().title("Vol").text("Le dragon vole").position(Pos.TOP_CENTER).showInformation();
    }

    @Override
    public void run() {
        Notifications.create().title("Course").text("Le dragon court").position(Pos.TOP_CENTER).showInformation();
    }

    /**
     * Permet au dragon de nager.
     */
    @Override
    public void swim() {
        Notifications.create().title("Nage").text("Le dragon nage").position(Pos.TOP_CENTER).showInformation();
    }

    /**
     * Permet au dragon de ressusciter.
     */
    public void revive() {
        super.setSante(getSanteMax());
        setEtat(Etat.PLEINE_FORME);
        setAge(Age.JEUNE);
        NotificationUtils.showNotification("Resurrection", "Le dragon ressucite");
    }

    /**
     * Permet au dragon de mourir. Quand il meurt, il ressuscite.
     */
    @Override
    public void die(){
        this.revive();
    }

}
