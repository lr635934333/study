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
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

public class MaxTempApp {
    public static void main(String[] args) throws Exception {
//       forSamplePartitioner(args);
       forCustomPartitioner(args);
    }

    private static void forSamplePartitioner(String[] args) throws Exception{
            // 设置jar包及作业名称
        int spiltSize = 2;

        Configuration configuration = new Configuration();

        FileSystem fileSystem = HadoopUtils.getFileSystem();
        fileSystem.delete(new Path(args[1]), true);

        //设置基本信息(configuration设置到Job后,下次使用需要通过job.getConfiguration()获取才能修改)
        Job job = Job.getInstance(configuration);
        job.setJarByClass(MaxTempApp.class);
        job.setJobName("MaxTempApp");

        //设置输入数据源,以及对应的Mapper(单个作业可以采用简单设置方式)
        MultipleInputs.addInputPath(job, new Path(args[0] + "/seq"),
                SequenceFileInputFormat.class, MaxTempSequenceMapper.class);

        //设置Mapper key-value输出类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(FloatWritable.class);

        //设置最大分片大小
        FileInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * Integer.parseInt(args[2]));

        //设置Combiner,reducer作为Combiner
        job.setCombinerClass(MaxTempReducer.class);

        //设置分区函数
        job.setPartitionerClass(TotalOrderPartitioner.class);

        //设置reduce
        job.setReducerClass(MaxTempReducer.class);
        job.setNumReduceTasks(spiltSize);

        //reducer输出key-value类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(FloatWritable.class);

        //设置输出文件格式
        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //设置采样器(放在设置的末尾)
        InputSampler.Sampler<IntWritable, FloatWritable> sampler =
                new InputSampler.RandomSampler<IntWritable, FloatWritable>(0.2, 2000, spiltSize);
        //设置分区文件,可以不用设置,会有默认的分区文件
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),  new Path(args[0] +"/par.list"));
        InputSampler.writePartitionFile(job, sampler);

        //启动作业
        job.waitForCompletion(true);
    }

    private static void forCustomPartitioner(String[] args) throws Exception{
        // 设置jar包及作业名称
        Configuration configuration = new Configuration();

        FileSystem fileSystem = HadoopUtils.getFileSystem();
        fileSystem.delete(new Path(args[1]), true);

        //设置基本信息(configuration设置到Job后,下次使用需要通过job.getConfiguration()获取才能修改)
        Job job = Job.getInstance(configuration);
        job.setJarByClass(MaxTempApp.class);
        job.setJobName("MaxTempApp");

        //设置输入数据源,以及对应的Mapper(单个作业可以采用简单设置方式)
        //job.setMapperClass(MaxTempSequenceMapper.class);
        //设置输入文件格式
        //job.setInputFormatClass(SequenceFileInputFormat.class);
        // 输入路径
        //FileInputFormat.addInputPath(job, new Path(args[0]));
        MultipleInputs.addInputPath(job, new Path(args[0]+"/text"),
                TextInputFormat.class, MaxTempTextMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[0] + "/seq"),
                SequenceFileInputFormat.class, MaxTempSequenceMapper.class);

        //设置Mapper key-value输出类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(FloatWritable.class);

        //设置最大分片大小
        FileInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * Integer.parseInt(args[2]) );


        //设置Combiner,reducer作为Combiner
        job.setCombinerClass(MaxTempReducer.class);

        //设置分区函数
        job.setPartitionerClass(MaxTempPartitioner.class);

        //设置reduce
        job.setReducerClass(MaxTempReducer.class);
        job.setNumReduceTasks(2);

        //reducer输出key-value类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(FloatWritable.class);

        //设置输出文件格式
        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //设置输出文件格式
        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 等待作业完成后退出
        job.waitForCompletion(true);
    }
}
