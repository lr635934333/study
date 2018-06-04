package com.liuran.web.utils.analysisdoucument.output;

import com.liuran.web.utils.analysisdoucument.bean.Word;

import java.util.ArrayList;
import java.util.List;

public class WordOutputConsole implements WordOutput{
    private int rank = 1;
    public WordOutputConsole(){

    }
    public WordOutputConsole(int rank){
        this.rank = rank;
    }
    @Override
    public void output(List<Word> words) {
        int index = 1;
        List<String> line = new ArrayList<>();
        String format = "";
        for (Word word : words){
            line.add(word.toString());
            format = format + "%-30s\t";
            if (index % rank == 0){
                System.out.println(String.format(format,line.toArray()));
                line = new ArrayList<>();
                format = "";
            }
            index ++;
        }

        if (line.size() != 0){
            System.out.println(String.format(format,line.toArray()));
        }
    }
}
