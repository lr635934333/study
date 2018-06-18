package com.liuran.hadoop.mr.mysql;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.UUID;

public class WordsCountReducer extends Reducer<Text, IntWritable, MySqlDBWritable, NullWritable>{

    private String info;

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        if (key.toString().length() > 30 || key.toString().length() <= 3){
            return;
        }

        int count = 0;
        for (IntWritable wordCount : values){
            count = count + wordCount.get();
        }

        MySqlDBWritable writable = new MySqlDBWritable();
        writable.setId(UUID.randomUUID().toString());
        writable.setWord(key.toString());
        writable.setCount(count);

        //reduce输出数据
        context.write(writable, NullWritable.get());

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
