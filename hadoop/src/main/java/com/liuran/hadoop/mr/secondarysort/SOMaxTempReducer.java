package com.liuran.hadoop.mr.secondarysort;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SOMaxTempReducer extends Reducer<SOMaxTempKeyWritable, NullWritable, IntWritable, FloatWritable>{
    @Override
    protected void reduce(SOMaxTempKeyWritable key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException {
        context.write(new IntWritable(key.getYear()), new FloatWritable(key.getTemp()));

        for (NullWritable v : values){

        }
    }
}
