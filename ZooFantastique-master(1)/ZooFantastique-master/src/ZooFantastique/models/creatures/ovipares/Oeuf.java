package ZooFantastique.models.creatures.ovipares;

import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.CreatureFactory;
import ZooFantastique.models.enclos.Enclos;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

import java.util.Random;


public class Oeuf extends Creature implements Runnable{

    private Ovipare mother;
    private int incubationTime = 60000;

    public Oeuf(String nom, Enclos enclos, String sonEmit, Ovipare mother) {
        super("Oeuf", enclos, "rrrr");
        this.mother = mother;
        this.setTaille(new Random().nextInt(1,2));
        this.setPoids(new Random().nextInt(3,8));
        Thread oeufThread = new Thread(this);
        oeufThread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(incubationTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().text("Un oeuf a eclos !").showInformation();
            }
        });
        this.leaveEnclos();
        new CreatureFactory().createCreature(mother.getNom(), mother.getEnclos());
    }

}
