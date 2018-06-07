package com.liuran.hadoop.maxtemp;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MaxTempPartitioner extends Partitioner<IntWritable, FloatWritable>{
    //使用分区函数实现全排序
    public int getPartition(IntWritable intWritable, FloatWritable floatWritable, int numPartitions) {
        if (intWritable.get() < 2000){
            return 0;
        } else {
            return 1;
        }
    }
}
