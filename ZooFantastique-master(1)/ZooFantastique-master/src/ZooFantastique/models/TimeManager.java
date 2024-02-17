package ZooFantastique.models;

import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * La classe TimeManager gère la gestion du temps dans le zoo fantastique.
 * Elle implémente l'interface Runnable pour permettre l'exécution en tant que thread.
 */
public class TimeManager implements Runnable {

    private static int jour = 1;
    private static String mois = "Janvier";
    private static int annee = 2023;
    private final EventManager eManager = new EventManager();
    private static TimeManager instance;

    private final ArrayList<String> listeMois = new ArrayList<>(Arrays.asList("Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"));
    /**
     * Constructeur de la classe TimeManager qui initialise la date de départ.
     */
    public static TimeManager getInstance(){
        if(instance == null){
            instance = new TimeManager();
        }
        return instance;
    }
    /**
     * Récupère la date actuelle sous forme de chaîne de caractères.
     *
     * @return La date actuelle au format jour mois année.
     */
    public String getDate(){
        return jour + " " + mois + " " + annee;
    }
    /**
     * Méthode exécutée lorsqu'un thread est démarré.
     * Elle gère l'avancement du temps et déclenche les événements liés au temps.
     */
    @Override
    public void run() {
        ArrayList<String> mois31Jours = new ArrayList<>(Arrays.asList("Janvier", "Mars", "Mai", "Juillet", "Août", "Octobre", "Décembre"));

        while(true) {
            try {
                Thread.sleep(500);
                if (jour == 28 && mois.equals("Fevrier")) {
                    jour = 1;
                    mois = "Mars";
                } else if (jour == 30 && !mois31Jours.contains(mois)) {
                    jour = 1;
                    mois = listeMois.get((listeMois.indexOf(mois) + 1) % 12);
                } else if (jour == 31 && mois31Jours.contains(mois)) {
                    if (mois.equals("Décembre")) {
                        annee++;
                    }
                    jour = 1;
                    mois = listeMois.get((listeMois.indexOf(mois) + 1) % 12);
                } else {
                    jour++;
                    eManager.handleZooEvent();
                }

                if(jour == 1 && mois.equals(Lycanthrope.getMoisSaisonAmour().get(0))){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Notifications.create().text("Debut de la saison des amours").position(Pos.TOP_CENTER).showInformation();
                        }
                    });
                }
                if(jour == 30 && mois.equals(Lycanthrope.getMoisSaisonAmour().get(Lycanthrope.getMoisSaisonAmour().size()-1))){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Notifications.create().text("Fin de la saison des amours").position(Pos.TOP_CENTER).showInformation();
                        }
                    });
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getMois() {
        return mois;
    }
}
