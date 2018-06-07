package com.liuran.web.utils.analysisdoucument;

import com.liuran.web.utils.analysisdoucument.documentloader.DocumentAnalysis;
import com.liuran.web.utils.analysisdoucument.documentloader.JavaDocumentAnalysis;

public class Main {

    public static void main(String[] args) {
//        String outputPath = "/Users/lr/file";
//
//        List<DocumentAnalysis> documentAnalyses = new ArrayList<>();
////        documentAnalyses.add(new ManDocumentAnalysis("/Users/lr/file/sourceCode/man"));
//        documentAnalyses.add(new JavaDocumentAnalysis("/Users/lr/file/sourceCode/java"));
//
//        Analysis analysis = new Analysis(4, documentAnalyses,
//                new WordOutputFile(outputPath,3), new WordOutputConsole(3));
//        analysis.doing();
//        analysis.outputByTop(1000);
        mergerFile();
    }

    public static void mergerFile(){
        DocumentAnalysis documentAnalyses = new JavaDocumentAnalysis("/Users/liuran/File/Program/sourceCode/hadoop-3.0.2-src");
        documentAnalyses.mergeAllFile("/Users/liuran/File/Program/sourceCode/hadoopSourceCode-merge.txt");
    }
}
