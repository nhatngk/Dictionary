package com.example.dicv2;

import Object.*;
import Control.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    public void runApplication(Stage stage) throws IOException {
        WordList wordList = new WordList();
        Parent root = FXMLLoader.load(DictionaryApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void addStage() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DictionaryApplication.class.getResource("add.fxml"));
        AnchorPane x = loader.load();
        Scene scene = new Scene(x);

        stage.setTitle("Add");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void start(Stage stage) throws IOException {
        runApplication(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}