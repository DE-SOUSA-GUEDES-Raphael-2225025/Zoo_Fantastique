package ZooFantastique.view;


import ZooFantastique.models.ZooFantastique;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.ZooMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ZooFantastiqueView implements Initializable {

    @FXML
    private Text zooNameText;


    private ZooFantastique zooFantastique;

    public ZooFantastiqueView(ZooFantastique zooFantastique){
        this.zooFantastique = zooFantastique;
    }

    public void showSuccessfullNameEdit(){
        System.out.println("Le nom de votre zoo est maintenant : " + zooFantastique.getNom());
    }

    public void displayAllEnclos(ArrayList<Enclos> listeEnclos) {
        for(int i = 0; i < listeEnclos.size(); ++i){
            System.out.println(i+1 + " - " + listeEnclos.get(i));
        }
    }

    public void displayView() throws IOException {
        AnchorPane scene = FXMLLoader.load(getClass().getResource("../fxml/ZooViewFXML.fxml"));
        ZooMain.getPrimaryStage().setScene(new Scene(scene));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

