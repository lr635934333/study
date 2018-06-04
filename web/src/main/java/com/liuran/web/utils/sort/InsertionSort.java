package com.liuran.web.utils.sort;
/**
 *插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 *它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * */
public class InsertionSort extends AbstractSort{
    public InsertionSort(){
        super();
    }

    public InsertionSort(boolean invert){
        super(invert);
    }

    @Override
    public Comparable[] sort(Comparable[] array) {
        for (int i = 1; i < array.length ; i ++){
            int preIndex = i -1;
            Comparable current = array[i];
            //在前i个有序的数列中插入一个数据
            while (true){
                if (preIndex < 0){
                    break;
                }
                if (fistGtSecond(current, array[preIndex])){
                    break;
                }
                array[preIndex + 1] = array[preIndex];

                preIndex --;
            }

            array[preIndex + 1] = current;
        }
        return array;
    }
}
