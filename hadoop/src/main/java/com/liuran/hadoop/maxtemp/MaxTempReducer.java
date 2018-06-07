package com.liuran.hadoop.maxtemp;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTempReducer extends Reducer<IntWritable, FloatWritable, IntWritable, FloatWritable>{
    @Override
    protected void reduce(IntWritable key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float max = Float.MIN_VALUE;
        for (FloatWritable value : values){
            max = max > value.get() ? max : value.get();
        }

        context.write(key, new FloatWritable(max));
    }
}
