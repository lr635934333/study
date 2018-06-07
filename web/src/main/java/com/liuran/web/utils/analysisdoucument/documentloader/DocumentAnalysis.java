package com.liuran.web.utils.analysisdoucument.documentloader;

import com.liuran.web.utils.analysisdoucument.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ND on 2017/10/16.
 * 读取文件或文件夹，并解析文档中的内容
 */
abstract public class DocumentAnalysis {
    private static final Set<String> specialSymbols = new HashSet<>();

    private String path;

    static {
        specialSymbols.add("~");
        specialSymbols.add("@");
        specialSymbols.add("#");
        specialSymbols.add("$");
        specialSymbols.add("%");
        specialSymbols.add("*");
        specialSymbols.add("&");
        specialSymbols.add("(");
        specialSymbols.add(")");
        specialSymbols.add("{");
        specialSymbols.add("}");
        specialSymbols.add("[");
        specialSymbols.add("]");
        specialSymbols.add("+");
        specialSymbols.add("_");
        specialSymbols.add("?");
        specialSymbols.add("-");
        specialSymbols.add(";");
        specialSymbols.add(":");
        specialSymbols.add(",");
        specialSymbols.add(".");
        specialSymbols.add("=");
        specialSymbols.add("'");
        specialSymbols.add("<");
        specialSymbols.add(">");
        specialSymbols.add("\"");
        specialSymbols.add("\\");
        specialSymbols.add("|");
        specialSymbols.add("/");
        for (int i=0 ; i < 10 ; i++){
            specialSymbols.add("" + i);
        }
    }

    abstract List<String> getAllDocument(List<String> lines);
    abstract Set<String> suffix();

    DocumentAnalysis(String path){
        this.path = path;
    }

    public List<File> getAllFile(){
        List<File> files = new ArrayList<>();
        getAllFile(files, new File(path));
        return files;
    }

    public List<String> readFileAndGetWords(File file){
        if (file == null || !file.isFile()){
            return new ArrayList<>();
        }
        List<String> fileLines = readFileAndGetAllLine(file);

        List<String> documentLineList =  getAllDocument(fileLines);

        List<String> words = new ArrayList<>();
        for (String line : documentLineList){
            words.addAll(getAllWords(line));
        }

        return words;
    }

    public List<String> getAllLine(File file){
        return readFileAndGetAllLine(file);
    }

    public void mergeAllFile(String outPut){
        File file = new File(outPut);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
             writer = new BufferedWriter(new FileWriter(file));
             for (File f : getAllFile()){
                 for (String line : readFileAndGetAllLine(f)){
                     writer.write(line);
                     writer.newLine();
                 }
             }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * readFileAndGetAllLine 方法提供允许子类重写，方便以后可以读取非文本文档，如 word、Excel 等
     * */
    protected List<String> readFileAndGetAllLine(File file){
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                line = line.trim();
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }


    private void getAllFile(List<File> fileList, File file){
        if (file.exists()){
            if (file.isFile()){
                //忽略统计程序产生的文件
                if (file.getName().endsWith(Constant.outputFileNameSuffix)){
                    return;
                }
                if (suffix() == null || suffix().size() == 0){
                    fileList.add(file);
                } else {
                    for (String suffix : suffix()){
                        if (file.getName().endsWith(suffix)){
                            fileList.add(file);
                            break;
                        }
                    }
                }
            } else {
                File[] list = file.listFiles();
                if (list != null){
                    for (File subFile : list){
                        getAllFile(fileList, subFile);
                    }
                }
            }
        }
    }

    private List<String> getAllWords(String line){
        String symbol = " ";
        if (line == null){
            return new ArrayList<>();
        }

        line = line.trim();

        //去掉中文
        String regex ="[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(line);
        line = mat.replaceAll(symbol);

        for (String sym : specialSymbols){
            line = line.replace(sym, symbol);
        }

        String[] words = line.split(symbol);
        List<String> resultWords = new ArrayList<>();
        for (String word: words){
            word = word.trim();
            if (!"".equals(word) && word.length() > 1){
                resultWords.add(word.toLowerCase());
            }
        }
        return resultWords;
    }

}
