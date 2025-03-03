package com.example.bigassproject;

public class Sudoku {
    final static int sudokuSize = 9;
    /*
    private int[][] sudokuArray = {
            {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}
    };
    */

    public void setNumber(int[][] grid, int row, int column, int input){
        grid[row][column] = input;
    }
    public void deleteNumber(int [][] grid, int row, int column){
        grid[row][column] = 0;
    }
    public void printSudoku(int[][] grid){
        for(int row = 0; row < sudokuSize; row++){
            for(int column = 0; column < sudokuSize; column++){
                System.out.print(grid[row][column] + " ");
            }
            System.out.println("");
        }
    }
    public boolean solveSudoku(int[][] grid, int row, int column){
        if(row == sudokuSize-1 && column == sudokuSize){
            return true;
        }
        if (column == sudokuSize){
            row++;
            column = 0;
        }
        if (grid[row][column] != 0){
            return solveSudoku(grid, row, column+1);
        }

        for (int number = 1; number < 10; number++) {
            if(testNumber(grid, row, column, number)){
                grid[row][column] = number;

                if (solveSudoku(grid, row, column+1)){
                    return true;
                }
            }
        }
        grid[row][column]=0;
        return false;
    }
    public boolean testNumber(int[][] grid, int row, int column, int number){
        //Test Rows
        for(int i = 0; i < 9; i++){
            if(grid[row][i] == number){return false;}
        }
        //Test Columns
        for(int i = 0; i < 9; i++){
            if(grid[i][column] == number){return false;}
        }
        //Test Box
        int box_X = row - (row % 3);
        int box_Y = column - (column % 3);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[box_X+i][box_Y+j] == number){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] sudokuArray = {
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}
        };
        Sudoku sudoku = new Sudoku();
        if(sudoku.solveSudoku(sudokuArray,0,0)){sudoku.printSudoku(sudokuArray);}
        else{
            System.out.println("No solution exists");
        }
    }
}
