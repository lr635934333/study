package com.liuran.hadoop.utils;

import java.util.*;

public class DataUtils {
    public static TreeMap<Integer, List<Float>> tempData(){
        TreeMap<Integer, List<Float>> result = new TreeMap<Integer, List<Float>>();
        for (int year = 2000; year <= 2200 ; year ++){
            int base = new Random().nextInt(10);
            Random random = new Random();
            List<Float> list = new ArrayList<Float>();
            for (int j = 0; j < 10000; j ++ ){
                float temp = base + (random.nextFloat() * 40 );
                list.add(temp);
            }

            result.put(year, list);
        }
        return result;
    }

    public static void testCreateTextTempData(){
        List<String> lines = new ArrayList<String>();
        TreeMap<Integer, List<Float>> content = tempData();
        for (Map.Entry<Integer, List<Float>> entry : content.entrySet()){
            for (Float value : entry.getValue()){
                lines.add("" + entry.getKey() +" " + value);
            }
        }
        FileUtils.createFile("/Users/liuran/File/Program/data/hadoop/data/temp/temp.txt", lines);
    }

    public static void testCreateSeqTempData(){
        HadoopUtils.createSeqFile("/Users/liuran/File/Program/data/hadoop/data/temp/seq/temp.seq",
                tempData());

    }

    public static void main(String[] args) {
        testCreateSeqTempData();
    }
}
