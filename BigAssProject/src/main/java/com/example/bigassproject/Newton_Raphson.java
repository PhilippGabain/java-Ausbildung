package com.example.bigassproject;

import java.util.Arrays;
import java.util.Objects;

public class Newton_Raphson {
    public static void main(String[] args){
        System.out.println("^");
        String example = " 2x^3 -4x^2 2x ";
        Newton_Raphson newtonRaphson = new Newton_Raphson();
        double[] result = newtonRaphson.newton_raphson_algorithm(example, 10);
        for(double d : result){
            System.out.println(d);
        }

    }
    public double[] newton_raphson_algorithm(String s, int precision){
        double[][] pairs = translateString(s);
        int counter = 0;
        int maxAnzahlNullstellen = 0;
        for(int i = 0; i < pairs.length; i++){
            if(pairs[i][1] > maxAnzahlNullstellen){
                maxAnzahlNullstellen = (int)pairs[i][1];
            }
        }
        double[] result = new double[maxAnzahlNullstellen];
        double x = 0;
        while()
        rekursion(pairs, x, 0, 10);

    }

    public double rekursion(double[][] pairs, double x, int counter, int precision){
        if(counter == precision){
            return x;
        }


        x = x - funktion(pairs, x)/ableitung(pairs, x);
        System.out.println("X = " + x);

        counter++;
        return rekursion(pairs, x, counter, precision);
    }

    public double funktion(double[][] pairs, double x){
        double result = 0;
        for(int i = 0; i < pairs.length; i++){
            result += pairs[i][0] * Math.pow(x, pairs[i][1]);
            System.out.println("result: " + result + ", pairs[i][0]: " + pairs[i][0] + ", x^pairs[i][0]: " + Math.pow(x, pairs[i][1]));
        }
        return result;
    }

    public double ableitung(double[][] pairs, double x){

        double result = 0;
        for(int i = 0; i < pairs.length; i++){
            result += (pairs[i][0] * pairs[i][1]) * Math.pow(x, pairs[i][1] - 1);
        }
        System.out.println(result);
        if(result == 0){
            return 1;
        }
        return result;
    }

    public double[][] translateString(String s){
        s = s.trim();
        String[] arr = s.split(" ");
        double[][] pairs = new double[arr.length][2];
        for(int i = 0; i < arr.length; i++){
            String[] tempArr1 = arr[i].split("x");
            double[] tempArr2 = new double[2];
            Arrays.fill(tempArr2, 1);
            for(int j = 0; j < tempArr1.length; j++){
                tempArr1[j] = tempArr1[j].replaceAll("\\^", "");
            }
            for(int j = 0; j < tempArr1.length; j++){
                if(!Objects.equals(tempArr1[j], "")){
                    tempArr2[j] = Double.parseDouble(tempArr1[j]);
                    System.out.println(tempArr2[j]);
                }
            }
            pairs[i] = tempArr2;
        }
        return pairs;
    }

}
