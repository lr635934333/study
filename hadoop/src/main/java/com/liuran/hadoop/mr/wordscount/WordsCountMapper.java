package com.liuran.hadoop.mr.wordscount;

import com.liuran.hadoop.utils.HadoopUtils;
import com.liuran.hadoop.utils.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordsCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private String info;

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        Text text = new Text();
        IntWritable intWritable = new IntWritable();

        for (String word : StringUtils.toWords(value.toString())){
            text.set(word);
            intWritable.set(1);
            //mapper输出内容
            context.write(text, intWritable);

            context.getCounter("map", getInfo()).increment(1);
        }
    }

    private String getInfo(){
        if (info != null){
            return info;
        }

        info = HadoopUtils.getInfo(this, "map");
        return info;
    }
}
