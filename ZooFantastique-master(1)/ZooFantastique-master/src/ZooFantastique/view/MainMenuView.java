package ZooFantastique.view;

import ZooFantastique.ZooMain;
import ZooFantastique.controllers.EnclosController;
import ZooFantastique.controllers.ZooFantastiqueController;
import ZooFantastique.models.enclos.Enclos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainMenuView implements Initializable {

    @FXML
    private Text zooNameText;

    @FXML
    private Text dateText;

    @FXML
    private GridPane enclosContainer;

    @FXML
    private GridPane actionButtonBox;

    @FXML
    private Button createEnclosButton;

    @FXML
    private TextArea createEnclosNameTextArea;

    private final int nbEnclosParLigne = 5;


    public MainMenuView() {

    }

    public void displayView() throws IOException {
        AnchorPane scene = FXMLLoader.load(getClass().getResource("../fxml/MainZooView.fxml"));
        ZooMain.getPrimaryStage().setScene(new Scene(scene));
    }

    public void initEnclosContainer(){
        ArrayList<Enclos> enclosList = ZooMain.getZoo().getListeDesEnclos();
        for(int i = 0; i < enclosList.size(); ++i) {
            Enclos enclos = enclosList.get(i);
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
            enclosContainer.add(enclosTile, i % nbEnclosParLigne, i / nbEnclosParLigne);

            enclosTile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new EnclosController().examinerEnclos(enclos);
                }
            });
        }

        enclosContainer.setHgap(15);
        enclosContainer.setVgap(15);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zooNameText.setText(ZooMain.getZoo().getNom());
        initEnclosContainer();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.25), e -> {
                    dateText.setText(ZooMain.getTps().getDate());
                })
        );

        createEnclosButton.setOnAction(actionEvent -> {
            new ZooFantastiqueController(ZooMain.getZoo()).creerEnclos();
        });

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
