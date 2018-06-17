package com.liuran.hadoop.mr.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordsCountFilterMapper extends Mapper<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void map(Text key, IntWritable value, Context context)
            throws IOException, InterruptedException {
        if (key.getLength() < 3){
            return;
        }

        context.write(key, value);
    }
}
