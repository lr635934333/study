package com.liuran.hadoop.mr.mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class WordsCountApp {
    public static void main(String[] args) throws Exception {
        // 设置jar包及作业名称
        Configuration configuration = new Configuration();

        //设置基本信息(configuration设置到Job后,下次使用需要通过job.getConfiguration()获取才能修改)
        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordsCountApp.class);
        job.setJobName("MySqlWordsCount");

        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://172.16.230.1/liuran";
        String user = "root";
        String password = "liuran";
        DBConfiguration.configureDB(job.getConfiguration(), driverClass, url, user, password);
        //配置输入为数据库
//        DBInputFormat.setInput(job, MySqlDBWritable.class ,
//                "select id, txt from word_txt", "select count(*) from word_txt");
//        job.setMapperClass(WordsCountDBMapper.class);
        //配置输入为文件
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(args[0]));
        job.setMapperClass(WordsCountFileMapper.class);

        //设置Mapper key-value输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce
        job.setReducerClass(WordsCountReducer.class);
        job.setNumReduceTasks(1);

        //reducer输出key-value类型
        job.setOutputKeyClass(MySqlDBWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输出文件格式
        DBOutputFormat.setOutput(job, "word_count", "id", "word", "count");
        // 等待作业完成后退出
        job.waitForCompletion(true);
    }
}
