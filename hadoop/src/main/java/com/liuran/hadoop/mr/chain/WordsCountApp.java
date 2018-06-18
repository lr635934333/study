package com.liuran.hadoop.mr.chain;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class WordsCountApp {
    public static void main(String[] args) throws Exception {
        // 设置jar包及作业名称
        Configuration configuration = new Configuration();

        FileSystem fileSystem = HadoopUtils.getFileSystem();
        fileSystem.delete(new Path(args[1]), true);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordsCountApp.class);
        job.setJobName("WordCountApp");
        job.setInputFormatClass(TextInputFormat.class);

        // 输入输出路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * Integer.parseInt(args[2]) );


        // 设置Mapper和Reducer实现
        job.setNumReduceTasks(1);

        ChainMapper.addMapper(job, WordsCountSplitMapper.class,
                LongWritable.class, Text.class, Text.class, IntWritable.class, job.getConfiguration());
        ChainMapper.addMapper(job, WordsCountFilterMapper.class,
                Text.class, IntWritable.class, Text.class, IntWritable.class, job.getConfiguration());
        ChainReducer.setReducer(job, WordsCountReducer.class,
                Text.class, IntWritable.class, Text.class, IntWritable.class, job.getConfiguration());
        ChainReducer.addMapper(job, WordsCountReducerMapper.class,
                Text.class, IntWritable.class, Text.class, IntWritable.class, job.getConfiguration());

        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 等待作业完成后退出
        job.waitForCompletion(true);
    }
}
