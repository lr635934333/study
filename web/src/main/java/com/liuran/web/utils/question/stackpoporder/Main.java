package com.liuran.web.utils.question.stackpoporder;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] push = new int[]{};
        int[] pop = new int[]{};
        System.out.println(isPopOrder(push, pop));
    }

    public static boolean isPopOrder(int[] pushArray, int[] popArray){
        if (popArray == null || pushArray == null || pushArray.length != popArray.length){
            return false;
        }

        Stack<Integer> stack = new Stack<>();

        int popIndex = 0;
        int pushIndex = 0;
        while (pushIndex < pushArray.length){
            stack.push(pushArray[pushIndex]);
            while (!stack.empty() && stack.peek() == popArray[popIndex]){
                stack.pop();
                popIndex ++;
            }
            pushIndex ++;
        }
        return stack.isEmpty();
    }
}
