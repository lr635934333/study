package com.liuran.web.utils.analysisdoucument;

import com.liuran.web.utils.analysisdoucument.bean.Word;
import com.liuran.web.utils.analysisdoucument.documentloader.DocumentAnalysis;
import com.liuran.web.utils.analysisdoucument.output.WordOutput;
import com.liuran.web.utils.analysisdoucument.output.WordStatistics;

import java.io.*;
import java.util.*;

/**
 * Created by ND on 2017/10/16.
 */
public class Analysis {

    private List<DocumentAnalysis> documentAnalyses = null;
    private WordStatistics wordStatistics;
    private List<WordOutput> wordOutputs = new ArrayList<>();
    private boolean isDoing = false;

    /**
     * @param documentAnalyses 文档加载器
     * */
    public Analysis(List<DocumentAnalysis> documentAnalyses, WordOutput output, WordOutput ... outputs) {
        if (documentAnalyses == null || documentAnalyses.size() == 0){
            throw new RuntimeException("documentAnalyses 不能为空");
        }

        this.documentAnalyses = documentAnalyses;
        wordStatistics = new WordStatistics();
        wordOutputs.add(output);
        wordOutputs.addAll(Arrays.asList(outputs));
    }

    /**
     * @param documentAnalyses 文档解析器
     * @param minWordLength 单词最小长度
     * @param output output器，可以指定一到多个output
     * */
    public Analysis(int minWordLength, List<DocumentAnalysis> documentAnalyses, WordOutput output, WordOutput ... outputs) {
        this(documentAnalyses, output, outputs);
        wordStatistics.setMinWordLength(minWordLength);
    }

    public void setFilterWord(Set<String> words){
        wordStatistics.setFilterWord(words);
    }

    public void outputByTop(int topN){
        checkDoing();
        List<Word> words = wordStatistics.display(topN);
        output(words);
    }

    public void outputByTimes(int times){
        checkDoing();
        List<Word> words = wordStatistics.displayByTimes(times);
        output(words);
    }

    public void doing(){
        for (DocumentAnalysis documentAnalysis : documentAnalyses){
            int index = 1;
            List<File> fileList = documentAnalysis.getAllFile();
            for (File file : fileList){
                System.out.println("count:" +fileList.size()+ "  current:" + index + " " + file.getName());

                wordStatistics.addWords(documentAnalysis.readFileAndGetWords(file));

                index ++;
            }
        }

        isDoing = true;
    }

    private void checkDoing(){
        if (!isDoing){
            throw new RuntimeException("请先执行 doing()");
        }
    }

    private void output(List<Word> words){
        if (wordOutputs.size() > 0){
            for (WordOutput wordOutput : wordOutputs){
                wordOutput.output(words);
            }
        }
        System.out.println("success total:" + words.size());
    }
}
