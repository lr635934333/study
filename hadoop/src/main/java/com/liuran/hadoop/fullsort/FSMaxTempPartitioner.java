package com.liuran.hadoop.fullsort;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class FSMaxTempPartitioner extends Partitioner<IntWritable, FloatWritable>{
    //使用分区函数实现全排序
    public int getPartition(IntWritable intWritable, FloatWritable floatWritable, int numPartitions) {
        if (intWritable.get() < 2100){
            return 0;
        } else {
            return 1;
        }
    }
}
