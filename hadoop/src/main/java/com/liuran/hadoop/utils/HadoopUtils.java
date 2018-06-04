package com.liuran.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HadoopUtils {
    private static FileSystem fileSystem;
    public static FileSystem getFileSystem(){
        if (fileSystem != null){
            return fileSystem;
        }

        synchronized (HadoopUtils.class){
            Configuration configuration = new Configuration();
            try {
                fileSystem = FileSystem.get(configuration);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileSystem;
    }

    public static void main(String[] args) {
        FileSystem fileSystem = getFileSystem();
        try {
            fileSystem.copyFromLocalFile(new Path("/Users/liuran/hadoop/words.txt"),
                    new Path("/Users/liuran/hadoop/words2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
