package com.example.bigassproject;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.Arrays;


public class SudokuController {
    @FXML
    private GridPane BigGrid;

    @FXML
    private GridPane box_0_0;


    @FXML
    public void initialize() {
        System.out.println("FXML GridPane loaded. Performing onload actions.");
        // force the field to be numeric only
        for(Node node : BigGrid.getChildren()){
            if(node instanceof GridPane){
                for(Node node1 : ((GridPane) node).getChildren()){
                    if(node1 instanceof TextField field){
                        field.textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                                String newValue) {
                                if (!newValue.matches("\\d*")) {
                                    field.setText(newValue.replaceAll("[^\\d]", ""));
                                }
                            }
                        });

                    }
                }
            }
        }

    }

    @FXML
    private Button solveSudoku;
    @FXML
    private Button goToMenu;

    Sudoku sudokuInstance = new Sudoku();

    public int[][] sudokuArray = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public TextField[][] fieldArray = new TextField[3][3];



    @FXML
    public void handlesolveSudoku(ActionEvent event) {
        int [][] sudokuArray = new int[9][9];
        Sudoku sudoku = new Sudoku();


        //Convert unsolved Sudoku to int[][] array
        for(int paneCol = 0; paneCol < 3 ; paneCol++){
            for(int paneRow = 1; paneRow < 4; paneRow++){
                GridPane nestedPane = (GridPane) getNodeByRowColumnIndex(paneRow, paneCol, BigGrid);
                TextField [][] box = new TextField[3][3];
                for(int boxCol = 0; boxCol < 3; boxCol++){
                    for(int boxRow = 0; boxRow < 3; boxRow++){
                        box[boxRow][boxCol] = (TextField) getNodeByRowColumnIndex(boxRow, boxCol, nestedPane);

                        int sudokuCol = paneCol*3 + boxCol;
                        int sudokuRow = (paneRow-1)*3 + boxRow;

                        if(box[boxRow][boxCol].getText() == ""){
                            sudokuArray[sudokuRow][sudokuCol] = 0;
                        }
                        else if (sudoku.testNumber(sudokuArray, sudokuRow, sudokuCol, Integer.parseInt(box[boxRow][boxCol].getText()))) {
                            sudokuArray[sudokuRow][sudokuCol] = Integer.parseInt(box[boxRow][boxCol].getText());
                        }
                        else {
                            System.out.println("No solution possible");
                            return;
                        }
                    }
                }
            }
        }



        //Print unsolved Sudoku in Command Shell
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(sudokuArray[i][j]+" ");
            }
            System.out.println();
        }

        //Solve Sudoku
        if(sudoku.solveSudoku(sudokuArray,0,0)){
            sudoku.printSudoku(sudokuArray);
            System.out.println("sudoku print works");

            //Convert solved Sudoku to Grid
            for(int sudokuRow = 0; sudokuRow < 9; sudokuRow++){
                for(int sudokuCol = 0; sudokuCol < 9; sudokuCol++){
                    int paneRow = ((int)(sudokuRow)/3)+1;
                    int paneCol = (int)sudokuCol/3;
                    GridPane nestedPane = (GridPane) getNodeByRowColumnIndex(paneRow, paneCol, BigGrid);
                    int boxRow = sudokuRow % 3;
                    int boxCol = sudokuCol % 3;

                    TextField field = (TextField) getNodeByRowColumnIndex(boxRow, boxCol, nestedPane);
                    field.setText(Integer.toString(sudokuArray[sudokuRow][sudokuCol]));

                }
            }
        }
        else{
            System.out.println("No solution exists");
        }




    }
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    @FXML
    public void start(Stage primaryStage) throws Exception {
        //create Scene Graph
        Parent root = FXMLLoader.load(getClass().getResource("Sudoku.fxml"));
        Scene scene = new Scene(root);
        //Create Window
        primaryStage.setTitle("Sudoku = Sepuku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

