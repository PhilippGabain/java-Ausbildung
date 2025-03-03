package com.example.bigassproject;
import java.util.Random;
import java.math.*;

public class HashFunction {
    public int hashfunction(String s){
        int hash = 7;
        for( int i = 0; i < s.length(); i++){
            hash = hash * 89 + s.charAt(i);
        }
        hash = hash % power(10,16);
        return hash;
    }
    public int power(int basis, int exponent){
        int result = basis;
        for(int i = 1; i < exponent; i++){
            result *= basis;
        }
        return result;
    }
}
