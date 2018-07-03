package com.liuran.web.utils.question.palindromicnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        String[] strings = bufferedReader.readLine().split(" ");
        int[] nums = new int[strings.length];
        for(int i =0;i<strings.length;i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }

        System.out.println(change(nums, 0, nums.length - 1));
    }

    //递归实现
    private static int change(int[] nums, int start, int end){
        if (start >= end){
            return 0;
        }

        if (nums[start]  == nums[end]){
            start ++;
            end --;
            return change(nums, start, end);
        } else if (nums[start] < nums[end]){
            start ++;
            nums[start] = nums[start - 1] + nums[start];
            return change(nums, start, end) + 1;
        } else {
            end --;
            nums[end] = nums[end] + nums[end + 1];
            return change(nums, start, end) + 1;
        }
    }

    private static int change(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        int times = 0;
        while (start < end){
            if (nums[start] == nums[end]){
                start ++;
                end --;
            } else if (nums[start] < nums[end]){
                int sum = nums[start] + nums[start + 1];
                start ++;
                nums[start] = sum;
                times ++;
            } else {
                int sum = nums[end] + nums[end - 1];
                end --;
                nums[end] = sum;
                times ++;
            }
        }
        return times;
    }
}
