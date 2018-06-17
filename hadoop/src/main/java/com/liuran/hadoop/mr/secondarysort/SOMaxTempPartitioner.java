package com.liuran.hadoop.mr.secondarysort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SOMaxTempPartitioner extends Partitioner<SOMaxTempKeyWritable, NullWritable>{

    public int getPartition(SOMaxTempKeyWritable soMaxTempKeyWritable, NullWritable nullWritable, int numPartitions) {
        return soMaxTempKeyWritable.getYear() % numPartitions;
    }
}
