package ZooFantastique.view;

import ZooFantastique.controllers.EnclosController;
import ZooFantastique.models.enclos.Enclos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCreatureView implements Initializable {

    @FXML
    private HBox buttonList;

    @FXML
    private Button retourButton;

    private Enclos enclos;
    private ArrayList<String> possibleCreatureSpeciesToAdd;


    public AddCreatureView(Enclos enclos, ArrayList<String> possibleCreatureSpeciesToAdd) {
        this.enclos = enclos;
        this.possibleCreatureSpeciesToAdd = possibleCreatureSpeciesToAdd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(possibleCreatureSpeciesToAdd.size());
        for(String creatureSpecies : possibleCreatureSpeciesToAdd){
            Button button = new Button(creatureSpecies);
            button.setPrefSize(100,50);
            button.setOnAction(actionEvent -> {
                new EnclosController().addCreature(enclos, button.getText());
            });
            buttonList.getChildren().add(button);
        }

        retourButton.setOnAction(actionEvent -> {
            new EnclosController().examinerEnclos(enclos);
        });
    }
}
