package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.Age;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.interfaces.IFly;
import ZooFantastique.models.interfaces.IRevive;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import utils.NotificationUtils;
import java.util.Random;

/**
 * La classe {@code Phoenix} représente une créature légendaire de type ovipare
 * qui a la capacité de voler et d'être ressuscitée. Elle étend les fonctionnalités
 * de la classe {@link Ovipare} et implémente les interfaces {@link IFly} et {@link IRevive}.
 * <p>
 * Le Phoenix peut pondre des œufs, voler majestueusement et revivre par magie.
 * </p>
 * <p>
 * @author [Raphael]
 * @version 1.0
 * @see Ovipare
 * @see IFly
 * @see IRevive
 */
public class Phoenix extends Ovipare implements IRevive, IFly {

    public Phoenix(Enclos enclos) {
        super("Phoenix", enclos, "Cuit cuit!");
        this.setTaille(new Random().nextInt(2,5));
        this.setPoids(new Random().nextInt(50,150));
    }


    /**
     * Permet au Phoenix de voler.
     */
    @Override
    public void fly() {
        Notifications.create().title("Vol").text("Le phoenix vole").position(Pos.TOP_CENTER).showInformation();
    }



    /**
     * Ressuscite le Phoenix.
     */
    @Override
    public void revive() {
        super.setSante(getSanteMax());
        setEtat(Etat.PLEINE_FORME);
        setAge(Age.JEUNE);
        NotificationUtils.showNotification("Resurrection", "Le phoenix ressucite");
    }


    /**
     * Permet de modifier la santé du Phoenix.
     * @param sante La nouvelle santé du Phoenix.
     */
    @Override
    public void setSante(double sante){
        super.setSante(sante);
        if(sante == 0) this.revive();
    }


    /**
     * Permet au Phoenix de mourir.
     * Quand le Phoenix meurt, il renait de ses cendres.
     */
    @Override
    public void die(){
        this.revive();
    }
}