package com.liuran.hadoop.maxtemp;

import com.liuran.hadoop.utils.HadoopUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MaxTempApp {
    public static void main(String[] args) throws Exception {
        // 设置jar包及作业名称
        Configuration configuration = new Configuration();

        FileSystem fileSystem = HadoopUtils.getFileSystem();
        fileSystem.delete(new Path(args[1]), true);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(MaxTempApp.class);
        job.setJobName("MaxTempApp");

        //设置最大分片大小
        FileInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * Integer.parseInt(args[2]) );

//        job.setMapperClass(MaxTempTextMapper.class);
//        job.setMapperClass(MaxTempSequenceMapper.class);
        //设置输入文件格式
//        job.setInputFormatClass(SequenceFileInputFormat.class);
        // 输入输出路径
//        FileInputFormat.addInputPath(job, new Path(args[0]));

        MultipleInputs.addInputPath(job, new Path(args[0]+"/text"),
                TextInputFormat.class, MaxTempTextMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[0] + "/seq"),
                SequenceFileInputFormat.class, MaxTempSequenceMapper.class);

        //设置输出文件格式
        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 设置Reducer个数
        job.setNumReduceTasks(2);
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
