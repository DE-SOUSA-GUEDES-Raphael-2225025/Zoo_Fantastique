package ZooFantastique.view;

import ZooFantastique.controllers.CreatureController;
import ZooFantastique.controllers.EnclosController;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Meute;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MeuteView implements Initializable {

    @FXML
    private GridPane lycantropeContainer;

    @FXML
    private Button backButton;

    private Meute meute;

    private final int nbLycanthropesParLign = 5;

    public MeuteView(Meute meute) {
        this.meute = meute;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseReleased(mouseEvent -> {
            new EnclosController().examinerEnclos(meute.getCoupleAlpha().getMaleAlpha().getEnclos());
        });
        int i = 0;
        ArrayList<Lycanthrope> lycanthropesList = meute.getLycanthropesPerDominationRank();
        for(Lycanthrope lycanthrope : lycanthropesList){
            VBox lycantropeView = new VBox();
            lycantropeView.setStyle("-fx-background-color:red");
            lycantropeView.setCursor(Cursor.HAND);
            lycantropeView.setAlignment(Pos.CENTER);
            lycantropeView.getChildren().add(new Text(lycanthrope.getNom()));
            lycantropeView.getChildren().add(new Text(lycanthrope.getRang().toString()));
            lycantropeView.setPrefSize(100,100);
            lycantropeView.setOnMouseReleased(mouseEvent -> {
                new CreatureController().visiterCreature(lycanthrope);
            });
            lycantropeContainer.add(lycantropeView, i % nbLycanthropesParLign, i / nbLycanthropesParLign);
            i++;
        }
    }
}
