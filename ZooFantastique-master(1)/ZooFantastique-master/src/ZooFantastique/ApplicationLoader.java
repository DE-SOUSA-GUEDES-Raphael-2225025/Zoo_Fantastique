package ZooFantastique;

import javafx.application.Application;
/**
 * La classe ApplicationLoader est responsable du chargement de l'application JavaFX ZooFantastique.
 * Elle lance l'application en utilisant la classe ZooMain comme classe principale.
 */
public class ApplicationLoader {
    /**
     * Point d'entrée principal de l'application. Lance l'application JavaFX ZooFantastique.
     *
     */
    public static void main(String[] args) {
        Application.launch(ZooMain.class, args);
    }
}
