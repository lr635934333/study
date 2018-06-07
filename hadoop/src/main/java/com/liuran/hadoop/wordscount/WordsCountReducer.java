package com.liuran.hadoop.wordscount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordsCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable wordCount : values){
            count = count + wordCount.get();
        }

        context.write(key, new IntWritable(count));

        context.getCounter("reduce", "WordsCountReducer.map").increment(1);
    }
}
