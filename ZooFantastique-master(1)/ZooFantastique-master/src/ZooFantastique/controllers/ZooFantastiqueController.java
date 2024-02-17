package ZooFantastique.controllers;

import ZooFantastique.models.ZooFantastique;
import ZooFantastique.view.CreateEnclosView;
import ZooFantastique.view.MainMenuView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ZooFantastique.ZooMain;

/**
 * La classe ZooFantastiqueController gère les opérations liées au zoo fantastique.
 */
public class ZooFantastiqueController{

    private ZooFantastique zooFantastique;

    /**
     * Constructeur de la classe ZooFantastiqueController.
     *
     * @param zooFantastique Le zoo fantastique à gérer.
     */
    public ZooFantastiqueController(ZooFantastique zooFantastique){
        this.zooFantastique = zooFantastique;
    }
    /**
     * Visite le zoo en affichant la vue du menu principal.
     */
    public void visitZoo(){
        try {
            new MainMenuView().displayView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Démarre le zoo avec le nom spécifié.
     *
     * @param nomZoo Le nom du zoo à démarrer.
     */
    public void startZoo(String nomZoo){
        zooFantastique.setNom(nomZoo);
        ZooMain.getTh().start();

        try{
            new MainMenuView().displayView();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    /**
     * Crée un nouvel enclos en affichant la vue de création d'enclos.
     */
    public void creerEnclos(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CreateEnclosViewFXML.fxml"));
        loader.setControllerFactory(c -> new CreateEnclosView());
        try{
            ZooMain.getPrimaryStage().setScene(new Scene(loader.load()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
