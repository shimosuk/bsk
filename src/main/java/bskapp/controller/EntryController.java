package bskapp.controller;

import bskapp.model.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EntryController {
    @FXML TextField teamRedName;
    @FXML TextField teamWhiteName;
    @FXML Button entryButton;

    @FXML
    protected void initialize() {
        entryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team.RED.setName(teamRedName.getText());
                Team.WHITE.setName(teamWhiteName.getText());
                ScoreController controller = new ScoreController();
                try {
                    Stage scoreStage = new Stage();
                    scoreStage.initModality(Modality.APPLICATION_MODAL);
                    controller.start(scoreStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
