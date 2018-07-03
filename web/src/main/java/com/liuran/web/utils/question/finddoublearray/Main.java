package com.liuran.web.utils.question.finddoublearray;

public class Main {
    public static void main(String[] args) {
//        int target = 5;
//        int[][] array = new int[][]{{1,2,3,4},{5,6,7,8}, {9,10,11}};
//        System.out.println(find(target, array));
        int[] array = new int[]{4,5,7,2,1};
        System.out.println(minNumberInRotateArray(array));
    }

    public static boolean find(int target, int[][] array){
        for (int[] arr : array){
            if (arr.length > 1){
                if (arr[0] <= target && arr[arr.length - 1] >= target){
                    int result = binarySearch(target, arr, 0, arr.length - 1);
                    if (result >= 0){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 1 2 3 4 5 6 7 8 9
     * 0 1 2 3 4 5 6 7 8
     * */
    public static int binarySearch(int target, int[] array, int start, int end){
        if (end < start){
            return -1;
        }

        if (start == end){
            if (array[start] == target){
                return start;
            } else {
                return -1;
            }
        }

        int between = (start + end) / 2;

        if (array[between] == target){
            return between;
        }

        if (array[between] > target){
            return binarySearch(target, array, start, between - 1);
        } else {
            return binarySearch(target, array, between + 1, end);
        }
    }

    public static int minNumberInRotateArray(int [] array) {
        if (array.length == 0){
            return 0;
        }
        int minIndex = 0;
        int[] temp = new int[array.length];
        for (int i = 1; i < array.length ; i ++){
            if (array[minIndex] > array[i]){
                minIndex = i;
                int j = 0;
                int tempIndex = 0;
                for (j = i ; j < array.length ; j ++, tempIndex ++){
                    temp[tempIndex] = array[j];
                }

                for (j = 0; j < minIndex ;j ++, tempIndex ++){
                    temp[tempIndex] = array[j];
                }
                array = temp;
                return minNumberInRotateArray(array);
            }
        }

        return array[0];
    }

}
