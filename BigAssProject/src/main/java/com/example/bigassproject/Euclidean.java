package com.example.bigassproject;

public class Euclidean {
    public int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
    public static void main(String[] args){
        Euclidean euclidean = new Euclidean();
        int a = 15684;
        int b = 9149;
        System.out.println("Greatest Common Denomiter: "+euclidean.gcd(a,b));
    }
}
