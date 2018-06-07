package com.liuran.hadoop.maxtemp;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTempMapper extends Mapper<LongWritable, Text, IntWritable, FloatWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] array = value.toString().split(" ");
        if (array.length > 1){
            context.write(new IntWritable(Integer.parseInt(array[0])),
                    new FloatWritable(Float.parseFloat(array[1])));
        }

    }
}
