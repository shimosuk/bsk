package bskapp.controller;

import bskapp.model.Player;
import bskapp.model.Team;
import bskapp.service.ScoreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StateController {
    private ScoreService service;
    ObservableList redTeamCells = FXCollections.observableArrayList();
    ObservableList whiteTeamCells = FXCollections.observableArrayList();
    @FXML private TextField entryRedNo;
    @FXML private TextField entryRedNm;
    @FXML private Button addRedMember;
    @FXML private TextField entryWhiteNo;
    @FXML private TextField entryWhiteNm;
    @FXML private Button addWhiteMember;
    @FXML private TableView redTableView;
    @FXML private TableView whiteTableView;
    @FXML private TableColumn redNoCol;
    @FXML private TableColumn redNmCol;
    @FXML private TableColumn redPFCol;
    @FXML private TableColumn redScoreCol;
    @FXML private TableColumn whiteNoCol;
    @FXML private TableColumn whiteNmCol;
    @FXML private TableColumn whitePFCol;
    @FXML private TableColumn whiteScoreCol;

    @FXML
    protected void initialize() {
        /* set refer to table columns */
        redTableView.setItems(redTeamCells);
        whiteTableView.setItems(whiteTeamCells);
        /* set table columns */
        redNoCol.setCellValueFactory(new PropertyValueFactory("number"));
        whiteNoCol.setCellValueFactory(new PropertyValueFactory("number"));
        redNmCol.setCellValueFactory(new PropertyValueFactory("name"));
        whiteNmCol.setCellValueFactory(new PropertyValueFactory("name"));
        redPFCol.setCellValueFactory(new PropertyValueFactory("foul"));
        whitePFCol.setCellValueFactory(new PropertyValueFactory("foul"));
        redScoreCol.setCellValueFactory(new PropertyValueFactory("total"));
        whiteScoreCol.setCellValueFactory(new PropertyValueFactory("total"));
        /* add table column values */
        redTeamCells.addAll(Team.RED.getMenber());
        whiteTeamCells.addAll(Team.WHITE.getMenber());

        service = new ScoreService();
        redTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    Player actor = (Player) redTableView.getSelectionModel().getSelectedItem();
                    Team team = Team.RED;
                    service.select(actor, team);
                }
            }
        });

        whiteTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    Player actor = (Player) whiteTableView.getSelectionModel().getSelectedItem();
                    Team team = Team.WHITE;
                    service.select(actor, team);
                }
            }
        });

        addRedMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player = new Player(Integer.parseInt(entryRedNo.getText()), entryRedNm.getText());
                Team.RED.entry(player);
                redTeamCells.add(player);
            }
        });
        addWhiteMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player = new Player(Integer.parseInt(entryWhiteNo.getText()), entryWhiteNm.getText());
                Team.WHITE.entry(player);
                whiteTeamCells.add(player);
            }
        });
    }

    @FXML
    protected void onePoint(ActionEvent event) {
        service.shotCount(1);
    }

    @FXML
    protected void twePoint(ActionEvent event) {
        service.shotCount(2);
    }

    @FXML
    protected void threePoint(ActionEvent event) {
        service.shotCount(3);
    }

    @FXML
    public void foul(ActionEvent event) {
        service.foul();
    }
}
