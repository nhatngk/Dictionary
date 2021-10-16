package com.example.dicv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    public void runApplication(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(DictionaryApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        runApplication(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}