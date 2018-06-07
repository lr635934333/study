package com.liuran.hadoop.wordscount;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


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
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 设置Mapper和Reducer实现
        job.setNumReduceTasks(1);
        job.setMapperClass(WordsCountMapper.class);
        job.setReducerClass(WordsCountReducer.class);

        //reducer作为Combiner
        job.setCombinerClass(WordsCountReducer.class);
//        job.setPartitionerClass();


        // 设置输出格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 等待作业完成后退出
        job.waitForCompletion(true);
    }
}
