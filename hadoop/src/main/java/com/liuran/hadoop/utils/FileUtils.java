package com.liuran.hadoop.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    public static void createFile(String file, List<String> context){
        BufferedWriter writer = null;
        try {
             writer = new BufferedWriter(new FileWriter(new File(file)));
             for (String line : context){
                 writer.newLine();
                 writer.write(line);
             }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
