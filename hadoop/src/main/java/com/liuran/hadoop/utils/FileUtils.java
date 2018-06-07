package com.liuran.hadoop.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    public static void testCreateTempData(){
        List<String> lines = new LinkedList<String>();
        int base = -10;
        for (int i = 1970; i <= 2020 ; i ++){
            Random random = new Random();
            for (int j = 0; j < 10000; j ++ ){
                float temp = base + (random.nextFloat() * 60 );
                String line = "" + i + " " + temp;
                lines.add(line);
            }
        }

        createFile("/Users/liuran/File/Program/data/hadoop/data/temp/temp.txt", lines);

    }

    public static void main(String[] args) {
        testCreateTempData();
    }

}
