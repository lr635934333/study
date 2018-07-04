package com.liuran.web.utils.question.sumn;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(sum(10));
        test();
    }

    public static int sum(int n){
        if(n <= 1){
            return n;
        }
        return n + sum(n - 1);
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        print(matrix, result, 0);
        return result;
    }


    public  static  void test(){
        int[][] matrix = new int[][]{{1},{3},{4},{5}};
        ArrayList<Integer> result = new ArrayList<>();
        print(matrix, result, 0);
        for (int val : result){
            System.out.println(val);
        }
    }

    public static void print(int[][] matrix, ArrayList<Integer> result, int times){


        if(matrix.length == 0){
            return;
        }
        int min = matrix.length > matrix[0].length ? matrix[0].length : matrix.length;
        int maxTimes = min % 2 == 0 ? min / 2: min /2 + 1;
        if(maxTimes <= times){
            return;
        }

        int y = matrix.length - times;
        int x = matrix[0].length - times;
        int i = times;
        int j = times;

        for(;i < x ; i ++){
            result.add(matrix[j][i]);
        }
        i --;

        j ++;
        for(;j < y; j ++){
            result.add(matrix[j][i]);
        }
        j --;

        i --;
        for(;i >= times; i --){
            result.add(matrix[j][i]);
        }
        i ++;

        j --;
        for(;j > times && times != maxTimes -1 ; j --){
            result.add(matrix[j][i]);
        }
        print(matrix, result, ++ times);
    }
}
