package ZooFantastique;

import ZooFantastique.models.Sexe;
import ZooFantastique.models.TimeManager;
import ZooFantastique.models.creatures.ovipares.Phoenix;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.ZooFantastique;
import ZooFantastique.models.enclos.Voliere;
import ZooFantastique.view.ZooWelcomeView;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
/**
 * La classe ZooMain est la classe principale de l'application ZooFantastique.
 * Elle étend la classe Application de JavaFX et définit le point d'entrée de l'application.
 */
public class ZooMain extends Application {

    private static ZooFantastique zoo = new ZooFantastique(10);
    private static Stage primaryStage;
    private static TimeManager tps = TimeManager.getInstance();
    private static Thread th = new Thread(tps);
    private static ArrayList<Meute> colonie = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        ZooMain.primaryStage = primaryStage;
        zoo.addEnclos(new Voliere("Enclos 1"));
        zoo.addEnclos(new Enclos("Enclos 2"));
        zoo.addEnclos(new Enclos("Enclos de rayan"));
        Enclos enclos3 = zoo.getListeDesEnclos().get(2);
        Lycanthrope lycanthrope = new Lycanthrope(enclos3, Sexe.MALE);
        Lycanthrope lycanthrope2 = new Lycanthrope(enclos3, Sexe.FEMELLE);
        enclos3.addMeute(new Meute(lycanthrope, lycanthrope2));
        new Lycanthrope(enclos3).rejoindreMeute(colonie.get(0));

        Phoenix phoenix = new Phoenix(zoo.getListeDesEnclos().get(0));
        primaryStage.setTitle("Zoo Fantastique");
        new ZooWelcomeView().displayView();
        primaryStage.show();
    }

    public static ZooFantastique getZoo(){
        return zoo;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static TimeManager getTps() {
        return tps;
    }

    public static ArrayList<Meute> getColonie() {
        return colonie;
    }

    public static Thread getTh() {
        return th;
    }
}
