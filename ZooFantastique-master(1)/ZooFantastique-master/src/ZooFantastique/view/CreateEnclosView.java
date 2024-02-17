package ZooFantastique.view;

import ZooFantastique.controllers.EnclosController;
import ZooFantastique.controllers.ZooFantastiqueController;
import ZooFantastique.ZooMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEnclosView implements Initializable {

    @FXML
    private ChoiceBox<String> typeEnclosDispo;

    @FXML
    private TextField enclosName;

    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeEnclosDispo.getItems().add("Aquarium");
        typeEnclosDispo.getItems().add("Voliere");
        typeEnclosDispo.getItems().add("Enclos terrestre");
        typeEnclosDispo.setValue("Enclos terrestre");

        createButton.setOnAction(event -> {
            new EnclosController().creerEnclos(enclosName.getText(), typeEnclosDispo.getValue().toString());
            new ZooFantastiqueController(ZooMain.getZoo()).visitZoo();
        });
    }
}
