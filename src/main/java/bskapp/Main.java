package bskapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getClassLoader().getResource("fxml/entry.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Scorer Book");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
