package ZooFantastique.controllers;

import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.CreatureFactory;
import ZooFantastique.models.ZooFantastique;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import ZooFantastique.models.enclos.*;
import ZooFantastique.view.AddCreatureView;
import ZooFantastique.view.EnclosView;
import ZooFantastique.ZooMain;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.NotificationUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe {@code EnclosController} est responsable de contrôler les interactions
 * liées aux enclos dans l'application. Elle facilite la communication entre
 * le modèle ({@link Enclos}) et la vue ({@link ZooFantastique.view.EnclosView}).
 * <p>
 *
 */
public class EnclosController {

    private ZooFantastique zoo;

    public EnclosController() {
        zoo = ZooMain.getZoo();
    }



    /**
     * Affiche des informations sur les enclos en utilisant la {@link ZooFantastique.view.EnclosView} associée.
     */
    public void examinerEnclos(Enclos enclos) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/EnclosViewFXML.fxml"));
        loader.setControllerFactory(c -> new EnclosView(enclos));

        try{
            ZooMain.getPrimaryStage().setScene(new Scene(loader.load()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Crée un nouvel enclos et l'ajoute au zoo.
     * @param nomEnclos
     * @param typeEnclos
     */
    public void creerEnclos(String nomEnclos, String typeEnclos) {
        Enclos nouvelEnclos = new EnclosFactory().build(typeEnclos, nomEnclos);
        zoo.addEnclos(nouvelEnclos);
    }


    /**
     * Renvoie les noms des créatures qui peuvent être ajoutées à l'enclos.
     * @param enclos
     */
    private ArrayList<String> getPossibleCreatureToAdd(Enclos enclos){
        if(!enclos.isEmpty()) return new ArrayList<>(Arrays.asList(enclos.getCreaturesPresentes().get(0).getNom()));
        if(enclos instanceof Aquarium) return new ArrayList<>(Arrays.asList("Kraken","Megalodon", "Sirene"));
        if(enclos instanceof Voliere) return new ArrayList<>(Arrays.asList("Dragon", "Phoenix"));
        else return new ArrayList<>(Arrays.asList("Lycanthrope", "Licorne","Nymphe"));
    }


    /**
     * Affiche la vue pour ajouter une créature à l'enclos.
     * @param enclos
     */
    public void addCreature(Enclos enclos){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AddCreatureView.fxml"));
        loader.setControllerFactory(c -> new AddCreatureView(enclos, getPossibleCreatureToAdd(enclos)));

        try{
            ZooMain.getPrimaryStage().setScene(new Scene(loader.load()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Ajoute une créature à l'enclos.
     * @param enclos
     * @param creatureName
     */
    public void addCreature(Enclos enclos, String creatureName){
        CreatureFactory creatureFactory = new CreatureFactory();
        Creature creature = creatureFactory.createCreature(creatureName, enclos);
        examinerEnclos(enclos);
    }


    /**
     * Nettoie l'enclos.
     * @param enclos
     */
    public void nettoyerEnclos(Enclos enclos){
        EnclosView enclosView = new EnclosView().setEnclos(enclos);
        if(enclos.getPropreteDegre() != Proprete.BON && enclos.isEmpty()){
            enclos.clean();
            Notifications.create().title("Enclos").text("L'enclos a été nettoyé").showConfirm();
            examinerEnclos(enclos);
        }
    }

    /**
     * Nourrit toutes les créatures de l'enclos.
     * @param enclos
     */
    public void nourrirAllCreatures(Enclos enclos){
        EnclosView enclosView = new EnclosView().setEnclos(enclos);
        enclos.nourrirAllCreature();
        enclosView.displaySuccessFeedMessage();
        Notifications.create().title("Enclos").text("Les créatures ont été nourries").hideAfter(Duration.seconds(1)).position(Pos.TOP_RIGHT).showInformation();
    }


    /**
     * Créer un meute dans un enclos
     */
    public void creerMeute(Lycanthrope male, Lycanthrope femmelle, Enclos enclos){
        enclos.addMeute(new Meute(male, femmelle));
        NotificationUtils.showNotification("Meute créee", "Une meute a été créee dans l'enclos " + enclos.getNom());
    }

}