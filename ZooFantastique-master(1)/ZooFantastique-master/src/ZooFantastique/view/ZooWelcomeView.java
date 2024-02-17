package ZooFantastique.view;

import ZooFantastique.ZooMain;
import ZooFantastique.controllers.ZooFantastiqueController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZooWelcomeView implements Initializable {

    @FXML
    private TextField zooNameArea;

    @FXML
    private Button confirmEditButton;


    public void displayView() throws IOException {
        AnchorPane scene = FXMLLoader.load(getClass().getResource("../fxml/ZooWelcomeView.fxml"));
        ZooMain.getPrimaryStage().setScene(new Scene(scene));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmEditButton.setCursor(Cursor.HAND);
        confirmEditButton.setOnAction(actionEvent -> {
            new ZooFantastiqueController(ZooMain.getZoo()).startZoo(zooNameArea.getText());
        });

    }
}
