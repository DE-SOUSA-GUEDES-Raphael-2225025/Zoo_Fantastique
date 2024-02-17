package ZooFantastique.controllers;

import ZooFantastique.ZooMain;
import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.Etat;
import ZooFantastique.models.enclos.Aquarium;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.enclos.Voliere;
import ZooFantastique.models.interfaces.IFly;
import ZooFantastique.models.interfaces.IRun;
import ZooFantastique.models.interfaces.ISwim;
import ZooFantastique.view.CreatureView;
import ZooFantastique.view.TransfereCreatureView;
import Exceptions.EnclosFullException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.controlsfx.control.Notifications;
import utils.NotificationUtils;

import java.util.ArrayList;

/**
 * La classe CreatureController gère les opérations liées aux créatures dans le zoo.
 */
public class CreatureController {



    /**
     * Émet le son de la créature donnée et l'affiche dans une notification.
     *
     * @param creature La créature pour laquelle émettre un son.
     */
    public void emettreSon(Creature creature){
        CreatureView creatureView = new CreatureView(creature);
        Notifications.create().text(creature.getSonEmit()).title(creature.getNom()).showInformation();
    }



    /**
     * Soigne la créature donnée, met à jour la vue et affiche une notification.
     *
     * @param creature La créature à soigner.
     */
    public void healCreature(Creature creature){
        creature.heal();
        visiterCreature(creature);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications.create().title("Soigner").text("La creature " + creature.getNom() + " a été soignée !").showError();
            }
        });
    }



    /**
     * Nourrit la créature donnée, met à jour la vue.
     *
     * @param creature La créature à nourrir.
     */
    public void nourrirCreature(Creature creature){
        creature.feed();
        visiterCreature(creature);
    }



    /**
     * Visite la vue de la créature donnée.
     *
     * @param creature La créature à visiter.
     */
    public void visiterCreature(Creature creature){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CreatureViewFXML.fxml"));
        loader.setControllerFactory(c -> new CreatureView(creature));

        try{
            ZooMain.getPrimaryStage().setScene(new Scene(loader.load()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * Transfère la créature donnée vers un enclos sélectionné.
     * Dans ce cas l'enclos n'est pas spécifié. On ouvre donc le menu de sélection d'enclos.
     *
     * @param creature La créature à transférer.
     */
    public void transfererCreature(Creature creature){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/TransfererCreatureView.fxml"));
        loader.setControllerFactory(c -> new TransfereCreatureView(creature,getEnclosPossibles(creature)));

        try{
            ZooMain.getPrimaryStage().setScene(new Scene(loader.load()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Transfère la créature donnée vers l'enclos spécifié.
     *
     * @param creature La créature à transférer.
     * @param enclos   L'enclos cible.
     */
    public void transfererCreature(Creature creature, Enclos enclos){
        creature.leaveEnclos();
        try{
            enclos.addCreature(creature);
            creature.setEnclos(enclos);
            Notifications.create().title("Transfert").text("Transféré avec succés").showConfirm();
        }catch (EnclosFullException e){
            Notifications.create().title("Enclos plein").text("L'enclos " + enclos.getNom() + " est plein").showError();
        }

        new EnclosController().examinerEnclos(creature.getEnclos());
    }



    /**
     * Récupère la liste des enclos possibles pour la créature en fonction de ses capacités.
     *
     * @param creature La créature pour laquelle trouver les enclos possibles.
     * @return Une liste d'enclos possibles pour la créature.
     */
    private ArrayList<Enclos> getEnclosPossibles(Creature creature){
        ArrayList<Enclos> enclosList = new ArrayList<>();
        for(Enclos enclos : ZooMain.getZoo().getListeDesEnclos()){
            if(creature instanceof ISwim && enclos instanceof Aquarium){
                enclosList.add(enclos);
            }
            else if(creature instanceof IFly && enclos instanceof Voliere){
                enclosList.add(enclos);
            }
            else if(creature instanceof IRun && !(enclos instanceof Aquarium) && !(enclos instanceof Voliere)){
                enclosList.add(enclos);
            }
        }
        return enclosList;
    }



    /**
     * Fait vieillir la créature et affiche une notification en cas de décès ou de vieillissement.
     *
     * @param creature La créature à faire vieillir.
     */
    public void viellir(Creature creature){
        creature.vieillir();
        if(creature.getEtat() == Etat.MORT){
            NotificationUtils.showErrorNotification("Mort", "La creature " + creature.getNom() + " est mort dans " + creature.getEnclos().getNom());
        }else{
            NotificationUtils.showErrorNotification(creature.getNom(), creature.getNom() + " a vieillit dans " + creature.getEnclos().getNom());
        }
    }
}
