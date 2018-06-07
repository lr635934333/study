package com.liuran.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.UnknownHostException;

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

    public static String getInfo(Object o, String msg){
        return getHost() + ":"+  getPID() +":" + getTid() + ":" + getObjInfo(o) + ":" + msg;
    }

    private static String getHost(){
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getPID(){
        try{
           String info = ManagementFactory.getCompilationMXBean().getName();
            return Integer.parseInt(info.substring(0, info.indexOf("@")));
        } catch (Exception e){
            return 0;
        }
    }

    private static String getTid(){
        return Thread.currentThread().getName();
    }

    private static String getObjInfo(Object o){
        return o.getClass().getSimpleName() + "@" + o.hashCode();
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
