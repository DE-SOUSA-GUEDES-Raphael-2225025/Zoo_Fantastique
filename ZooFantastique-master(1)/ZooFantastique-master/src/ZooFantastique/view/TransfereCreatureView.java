package ZooFantastique.view;

import ZooFantastique.controllers.CreatureController;
import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.enclos.Enclos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransfereCreatureView implements Initializable {


    @FXML
    private HBox enclosPossibleContainer;

    private Creature creature;
    private ArrayList<Enclos> enclosPossibles;

    public TransfereCreatureView(Creature creature, ArrayList<Enclos> enclosPossibles) {
        this.creature = creature;
        this.enclosPossibles = enclosPossibles;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Enclos enclos : enclosPossibles){
            Enclos current = enclos;

            VBox enclosTile = new VBox();
            Text enclosTypeText = new Text(enclos.getClass().getSimpleName());
            enclosTile.getChildren().add(enclosTypeText);
            Image image = new Image("/assets/enclosIcons/" + enclos.getClass().getSimpleName() + ".png");
            ImageView iconEnclos = new ImageView(image);
            iconEnclos.setFitHeight(100);
            iconEnclos.setFitWidth(100);
            enclosTile.getChildren().add(iconEnclos);
            enclosTile.getChildren().add(new Text(enclos.getNom()));
            enclosTile.getChildren().add(new Text(String.valueOf(enclos.getNbCreaturePresente()) + " Créatures"));
            enclosTile.setAlignment(Pos.CENTER);
            enclosTile.setPrefSize(100, 100);
            enclosTile.setCursor(Cursor.HAND);


            enclosTile.setOnMouseReleased(mouseEvent -> {
                new CreatureController().transfererCreature(creature, current);
            });
            enclosPossibleContainer.getChildren().add(enclosTile);
        }
    }

}
