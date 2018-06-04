package com.liuran.web.utils.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortMain {
    private static final Logger LOG = LoggerFactory
            .getLogger(SortMain.class);

    public static void main(String[] args) {
        testAverageTime();
    }

    public static void testSystemSortAndMySort(){
        List<Integer> collection1 = new ArrayList<>();
        List<Integer> collection2 = new ArrayList<>();

        Random random = new Random();
        for (int i=0 ;i < 10000 ;i++){
            int value = random.nextInt(500000 * 2);
            collection1.add(value);
            collection2.add(value);
        }
        long start = System.currentTimeMillis();
        Collections.sort(collection1);
        System.out.println("Collections:" +( System.currentTimeMillis() - start));

        Sort sort = new QuickSort();
        long start2= System.currentTimeMillis();
        List<Integer> result = sort.sort(collection2);
        System.out.println("Sort:" + (System.currentTimeMillis() - start2));
    }

    public static void testAverageTime(){

        Sort quick = new QuickSort();
        Sort merge = new MergeSort();
        Sort heap = new HeapSort();

        test(quick, 2, 100);
        test(merge, 2,  100);
        test(heap, 2, 100);

        System.out.println("====");
        int capacity = 1000;
        for (int i = 0; i < 20 ;i ++){

            capacity = capacity * 2;

            if (capacity > 10000000){
                return;
            }

            LOG.info("");
            LOG.info("数据量:" + capacity);
            test(quick, 20, capacity);
            test(merge, 20, capacity);
            test(heap, 20, capacity);

        }
    }

    public static void test(Sort sort, int times, int capacity){

        long total = 0;
        int timesTemp = times;
        while (times > 0){
            List<Integer> collection = new ArrayList<>();
            Random random = new Random();
            for (int i=0 ;i < capacity ;i++){
                int value = random.nextInt(capacity * 2);
                collection.add(value);
            }

            long sortTime = sort.sortTime(collection);

            total = total + sortTime;

            times --;
        }

        String className = sort.getClass().getName();
        LOG.info(className.substring(className.lastIndexOf(".") + 1) + "耗时:" +
                (total / timesTemp));

    }
}
