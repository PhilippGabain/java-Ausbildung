package com.example.bigassproject;

import java.util.Arrays;

public class Gauss_Seidel {
    public static void main(String[] args) {
        Gauss_Seidel gaussSeidel = new Gauss_Seidel();
        int n = 5;
        double[][] matrix = gaussSeidel.create_matrix(n);
        double[] res = new double[n];
        Arrays.fill(res, 0);
        double[][] bsp_mtrx = {{4,-1, 1},{2,5,2},{1,2,3}};
        double[] bsp_res = {7,-4, 3};
        gaussSeidel.print_matrix(bsp_mtrx);
        double[] x_values = gaussSeidel.wrapper(bsp_mtrx, bsp_res, 10);
        gaussSeidel.print_arr(x_values);




    }

    public void print_matrix(double[][] mtrx){
        int n = mtrx.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(mtrx[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void print_arr(double[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++){
            System.out.println(arr[i] + " ");
        }
    }

    public double[][] create_matrix(int n){
        double[][] matrix = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public double[] wrapper(double[][] matrix, double[] res, int precision){
        int counter = 0;
        int n = res.length;
        double[] x_values = new double[n];
        Arrays.fill(x_values, 0);
        return algorythm(matrix, res, x_values, precision, counter, n);
    }

    public double[] algorythm(double[][] matrix, double[] res, double[] x_values, int precision, int counter, int n){
        if(counter > precision){
            return x_values;
        }

        for(int i = 0; i < n; i++){
            double new_res = res[i];
            for(int j = 0; j < n; j++){
                if(j != i){
                    new_res -= matrix[i][j] * x_values[j];
                }
            }
            new_res /= matrix[i][i];
            x_values[i] = new_res;
        }
        counter++;
        return algorythm(matrix, res, x_values, precision, counter, n);
    }

}
