package com.liuran.web.utils.question.climbstairs;

public class Main {
    public static void main(String[] args) {
        System.out.println(stair(10));
    }

    private static int stair(int num){
        if (num < 1){
            return 0;
        }

        //如果只有一个台阶,只有一种走法
        if (num == 1){
            return 1;
        }

        //如果只有两个台阶,只有两种周法(1步一个台阶,或者两步一个台阶)
        if (num == 2){
            return 2;
        }

        //如果有n个台阶有两种情况(最后剩一个台阶或者最后剩两个台阶)
        //即: 前sum(n-1) + sum(n-2)
        return stair(num - 1) + stair(num - 2);
    }
}
