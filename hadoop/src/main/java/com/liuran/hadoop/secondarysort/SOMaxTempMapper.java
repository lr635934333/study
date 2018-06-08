package com.liuran.hadoop.secondarysort;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SOMaxTempMapper extends Mapper<IntWritable, FloatWritable, SOMaxTempKeyWritable, NullWritable>{
    @Override
    protected void map(IntWritable key, FloatWritable value, Context context) throws IOException, InterruptedException {
        SOMaxTempKeyWritable maxTempKey = new SOMaxTempKeyWritable();
        maxTempKey.setYear(key.get());
        maxTempKey.setTemp(value.get());
        context.write(maxTempKey, NullWritable.get());
    }
}
