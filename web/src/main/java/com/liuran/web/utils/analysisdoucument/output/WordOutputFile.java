package com.liuran.web.utils.analysisdoucument.output;

import com.liuran.web.utils.analysisdoucument.Constant;
import com.liuran.web.utils.analysisdoucument.bean.Word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordOutputFile implements WordOutput{
    private String filePath = null;

    private int rank = 1;
    public WordOutputFile(String path){
        this.filePath = path + "/" + Constant.outputFileName + "." + Constant.outputFileNameSuffix;
    }

    public WordOutputFile(String path, int rank){
        this(path);
        this.rank = rank;
    }
    @Override
    public void output(List<Word> words) {
        File file = new File(filePath);
        if (!file.exists() && file.isDirectory()){
            throw new RuntimeException("文件不存在");
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            int index = 1;
            List<String> line = new ArrayList<>();
            String format = "";
            for (Word word : words){
                line.add(word.toString());
                format = format + "%-30s\t";
                if (index % rank == 0){
                    writer.write(String.format(format,line.toArray()));
                    writer.newLine();
                    line = new ArrayList<>();
                    format = "";
                }
                index ++;
            }

            if (line.size() != 0){
                writer.write(String.format(format,line.toArray()));
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
