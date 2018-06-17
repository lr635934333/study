package com.liuran.hadoop.mr.wordscount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区函数
 * 泛型KEY:Map输出KEY
 * 泛型VALUE:Map输出VALUE
 * */
public class WordsCountPartitioner extends Partitioner<Text, IntWritable>{
    /**
     * @param numPartitions 总分区数,等于reducer数
     * */
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        return 0;
    }
}
