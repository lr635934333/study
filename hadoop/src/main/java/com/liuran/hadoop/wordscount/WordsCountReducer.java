package com.liuran.hadoop.wordscount;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordsCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

    private String info;

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable wordCount : values){
            count = count + wordCount.get();
        }

        //reduce输出数据
        context.write(key, new IntWritable(count));

        //计数器
        context.getCounter("reduce", getInfo()).increment(1);
    }

    private String getInfo(){
        if (info != null){
            return info;
        }

        info = HadoopUtils.getInfo(this, "map");
        return info;
    }
}
