package com.example.bigassproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sudoku.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1600, 1200));

        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}