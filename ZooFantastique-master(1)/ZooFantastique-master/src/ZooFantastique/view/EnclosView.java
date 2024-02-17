package ZooFantastique.view;

import ZooFantastique.ZooMain;
import ZooFantastique.controllers.CreatureController;
import ZooFantastique.controllers.EnclosController;
import ZooFantastique.controllers.MeuteController;
import ZooFantastique.controllers.ZooFantastiqueController;
import ZooFantastique.models.creatures.Creature;
import ZooFantastique.models.creatures.vivipares.lycanthrope.Lycanthrope;
import ZooFantastique.models.enclos.Enclos;
import ZooFantastique.models.enclos.Proprete;
import javafx.application.Platform;
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

public class EnclosView implements Initializable {

    @FXML
    private Text enclosName;

    @FXML
    private GridPane creaturesContainer;

    @FXML
    private Button returnButton;

    @FXML
    private Text enclosNameText;

    @FXML
    private Text enclosSuperficieText;

    @FXML
    private Text enclosNbCreaturesText;

    @FXML
    private Text enclosNbCreaturesMaxText;

    @FXML
    private Text prepreteDegreText;

    @FXML
    private Button cleanButton;

    @FXML
    private Button feedAllButton;

    @FXML
    private Button addCreatureButton;

    private Enclos enclos;
    private final int creaturePerRow = 5;

    public EnclosView(){}

    public EnclosView(Enclos enclos){
        this.enclos = enclos;
    }

    public void displayEnclosVisitMessage(){
        System.out.println("Vous entrez dans l'enclos " + enclos.getNom());
    }

    public void displayCreaturesInside(){
        for (Creature creature : enclos.getCreaturesPresentes()){
            creature.toString();
        }
    }

    public void displayPossibleCreatureToAdd(ArrayList<String> createNameList){
        for(int i = 0; i < createNameList.size(); i++){
            System.out.println( (i+1) + " - " + createNameList.get(i));
        }
    }

    public void displayCreatures(ArrayList<Creature> creatures){
        for (Creature creature : creatures){
            System.out.println(creature.toString());
        }
    }

    public void displayEnclosPossibleActions(){
        System.out.println("Q - Quitter l'enclos");
        if(enclos.getNbCreaturePresente() < enclos.getNbCreatureMax()){
            System.out.println("A - Ajouter une créature");
        }
        System.out.println("D - Afficher les créatures");
    }

    public void displayCreatureSelectionError(){
        System.out.println("Mauvais identifiant de créature");
    }

    public void displayEnclosAlreadyCleanMessage(){
        System.out.println("L'enclos est déja propre !");
    }

    public void displayEmptyNameError(){
        System.out.println("Nom invalide, l'enclos ne peut pas avoir un nom vide");
    }

    public void displaySuccessFullNameEditMessage(){
        System.out.println("Le nom de l'enclos a été changé avec succès");
    }

    public void displaySuccessFeedMessage(){
        System.out.println("Toutes les créature ont été nourries avec succès");
    }

    public void displayMeute(){
        if(enclos.getMeute() != null){
            VBox meuteVBox = new VBox();
            meuteVBox.setAlignment(Pos.CENTER);
            meuteVBox.getChildren().add(new Text("Meute de Lycanthropes"));
            meuteVBox.setPrefSize(100, 100);
            meuteVBox.setStyle("-fx-background-color: red");
            meuteVBox.setCursor(Cursor.HAND);
            creaturesContainer.add(meuteVBox, 0, 0);

            meuteVBox.setOnMouseClicked(event -> {
                new MeuteController().visitMeute(enclos.getMeute());
            });
        }
    }

    public void displayCreatures(){
        int i = 0;
        if(enclos.getMeute() != null){
            i = 1;
        }
        for(; i < enclos.getNbCreaturePresente(); ++i){
            Creature creature = enclos.getCreaturesPresentes().get(i);

            if(creature instanceof Lycanthrope){
                if(((Lycanthrope) creature).getMeute() != null){
                    continue;
                }
            }

            VBox creatureVBbox = new VBox();
            creatureVBbox.setAlignment(Pos.CENTER);
            creatureVBbox.getChildren().add(new Text(creature.getNom()));
            creatureVBbox.setPrefSize(100, 100);
            creatureVBbox.setStyle("-fx-background-color: green");
            creatureVBbox.setCursor(Cursor.HAND);
            creaturesContainer.add(creatureVBbox, i%creaturePerRow, i/creaturePerRow);

            creatureVBbox.setOnMouseClicked(event -> {
                new CreatureController().visiterCreature(creature);
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayCreatures();
        displayMeute();
        returnButton.setOnAction(event -> {
            new ZooFantastiqueController(ZooMain.getZoo()).visitZoo();
        });
        cleanButton.setOnAction(event -> {
            (new EnclosController()).nettoyerEnclos(enclos);
        });
        feedAllButton.setOnAction(event -> {
            (new EnclosController()).nourrirAllCreatures(enclos);
        });
        addCreatureButton.setOnAction(event -> {
            (new EnclosController()).addCreature(enclos);
        });

        initButtonIfNeeded();
        enclosNameText.setText(enclos.getNom());
        enclosSuperficieText.setText(String.valueOf(enclos.getSuperficie()));
        enclosNbCreaturesText.setText(String.valueOf(enclos.getNbCreaturePresente()));
        enclosNbCreaturesMaxText.setText(String.valueOf(enclos.getNbCreatureMax()));
        prepreteDegreText.setText(String.valueOf(enclos.getPropreteDegre()));
    }

    public void initButtonIfNeeded(){
        if(enclos.getPropreteDegre() == Proprete.BON || !enclos.isEmpty()){
            cleanButton.setVisible(false);
            cleanButton.setManaged(false);
        }
        if(enclos.getNbCreaturePresente() == enclos.getNbCreatureMax()){
            addCreatureButton.setVisible(false);
            addCreatureButton.setManaged(false);
        }
    }

    public EnclosView setEnclos(Enclos enclos){
        this.enclos = enclos;
        return this;
    }
}
