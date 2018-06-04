package com.liuran.web.utils.sort;
/**
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的
 * 分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，
 * 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 * 重点:
 * 1、采用策略把一个数组才分成两个数组进行归并排序
 * 2、合并两个有序数组的策略
 * */
public class MergeSort extends AbstractSort{

    public MergeSort(){
        super();
    }

    public MergeSort(boolean invert){
        super(invert);
    }

    @Override
    public Comparable[] sort(Comparable[] array) {
        Comparable[] temp = new Comparable[array.length];

        mergerSort(array, 0, array.length - 1, temp);
        return array;
    }

    /**
     * @param array 需要进行排序的数组
     * @param left 左子数组其实位置
     * @param right 右子数组其实位置
     * @param temp 排序缓存数组(用着合并两个数组缓存用),缓存数组长度等于排序数组长度
     * */
    private void mergerSort(Comparable[] array, int left ,int right, Comparable[] temp){
        if (left >= right){
            return;
        }

        //把数组拆分成两个数组
        int mid = ((left + right) / 2 );
        mergerSort(array, left, mid, temp);
        mergerSort(array, mid + 1, right ,temp);

        //合并排序好的两个数组
        mergeArray(array, left, mid, right, temp);
    }

    /**
     * @param array 数组原始数据
     * @param left 左子数组其实位置(包含left节点)
     * @param right 右子数组结束位置(包含right节点)
     * @param mid 两个数组中间分隔位置(mid节点归左子树)
     * */
    private void mergeArray(Comparable[] array, int left, int mid, int right, Comparable[] temp){
        //左数组index
        int leftIndex = left;
        //右数组index
        //mergerSort(array, mid + 1, right ,temp)和此处代码相对应
        int rightIndex = mid + 1;
        int tempIndex = 0;
        //合并数组交叉部分
        while (leftIndex <= mid && rightIndex <= right){
            if (fistLteSecond(array[leftIndex], array[rightIndex])){
                temp[tempIndex ++] = array[leftIndex];
                leftIndex ++;
            } else {
                temp[tempIndex ++] = array[rightIndex];
                rightIndex ++;
            }
        }

        //和并两个数组非交叉部分(这一步最多只有一个while循环需要执行)
        while (leftIndex <= mid){
            temp[tempIndex ++] = array[leftIndex ++];
        }

        while (rightIndex <= right){
            temp[tempIndex ++] = array[rightIndex ++];
        }

        //把排序好的数组copy到原数组中
        int copyIndex = 0;
        while (left <= right){
            array[left ++] = temp[copyIndex ++];
        }

    }
}
