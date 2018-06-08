package com.liuran.hadoop.fullsort;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FSMaxTempSequenceMapper extends Mapper<IntWritable, FloatWritable, IntWritable, FloatWritable>{
    @Override
    protected void map(IntWritable key, FloatWritable value, Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }
}
