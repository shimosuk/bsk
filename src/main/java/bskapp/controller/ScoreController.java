package bskapp.controller;

import bskapp.model.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.net.URL;

public class ScoreController {
    @FXML private Label teamRedName;
    @FXML private Label teamWhiteName;
    @FXML private Label redTotalScore;
    @FXML private Label whiteTotalScore;
    @FXML private ProgressBar teamRedFoul;
    @FXML private ProgressBar teamWhiteFoul;
    @FXML private StateController childStateController;

    @FXML
    protected void initialize() {
        teamRedName.setText(Team.RED.getName());
        teamWhiteName.setText(Team.WHITE.getName());

        redTotalScore.textProperty().bind(Team.RED.totalProperty().asString());
        whiteTotalScore.textProperty().bind(Team.WHITE.totalProperty().asString());
        teamRedFoul.progressProperty().bind(Team.RED.foulProperty());
        teamWhiteFoul.progressProperty().bind(Team.WHITE.foulProperty());
    }

    public void start(Stage stage) throws Exception {
        URL url = getClass().getClassLoader().getResource("fxml/scoreBoard.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Scorer Book");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
