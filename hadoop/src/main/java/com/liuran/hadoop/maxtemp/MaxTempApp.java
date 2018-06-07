package com.liuran.hadoop.maxtemp;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTempApp {
    public static void main(String[] args) throws Exception {
        // 设置jar包及作业名称
        Configuration configuration = new Configuration();

        FileSystem fileSystem = HadoopUtils.getFileSystem();
        fileSystem.delete(new Path(args[1]), true);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(MaxTempApp.class);
        job.setJobName("MaxTempApp");
        job.setInputFormatClass(TextInputFormat.class);

        // 输入输出路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        //设置最大分片大小
        FileInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * Integer.parseInt(args[2]) );
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 设置Mapper和Reducer实现
        job.setNumReduceTasks(2);
        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);

        //reducer作为Combiner
        job.setCombinerClass(MaxTempReducer.class);
        //设置分区函数
        job.setPartitionerClass(MaxTempPartitioner.class);

        // 设置输出格式
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(FloatWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(FloatWritable.class);

        // 等待作业完成后退出
        job.waitForCompletion(true);
    }
}
