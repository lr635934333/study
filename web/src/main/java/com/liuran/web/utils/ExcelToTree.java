package com.liuran.web.utils;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 <dependency>
 <groupId>net.sourceforge.jexcelapi</groupId>
 <artifactId>jxl</artifactId>
 <version>2.6.12</version>
 </dependency>
 */
public class ExcelToTree {
    public static void main(String[] args) {
        List<Tree<String>> trees = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File("/Users/liuran/Desktop/menu.xls"));
            ExcelToTree excelToTree = new ExcelToTree();
            Excel excel = excelToTree.readExcel(inputStream);
            if (excel == null || excel.getSheets().size() < 1){
                return;
            }
            List<Line> lines =  excel.getSheets().get(0).getAllLine();

            trees = excelToTree.getTree(lines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return;
    }

    private void saveDB(Tree<String> tree){
        //save to db

        if (tree.getChild() != null){
            for (Tree<String> node : tree.getChild()){
                saveDB(node);
            }
        }
    }

    private List<Tree<String>> getTree(List<Line> lines){
        checkExcel(lines);
        List<Tree<String>> trees = new ArrayList<>();
        Stack<Tree<String>> stack = new Stack<>();

        int lastIndex = 0;
        for (Line line : lines){
            String[] cells = line.getCells();
            int currentIndex = cells.length;
            //todo
            Tree<String> node = new Tree<>(cells[cells.length - 1]);

            while (lastIndex >= currentIndex){
                stack.pop();
                lastIndex --;
            }

            if (currentIndex == 1){
                trees.add(node);
            } else {
                Tree<String> parent = stack.peek();
                parent.addChild(node);
                node.setParent(parent);
            }
            stack.push(node);
            lastIndex ++;
        }
        return trees;
    }

    private Excel readExcel(InputStream fis){
        Excel excel = new Excel();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(fis);
            List<Sheet> sheets = new ArrayList<>();

            for (jxl.Sheet sheet :book.getSheets()){
                List<Line> allLine = new ArrayList<>();
                int rows = sheet.getRows();
                for(int i=0; i< rows; i ++){
                    Line line = new Line(sheet.getRow(i));
                    allLine.add(line);
                }
                Sheet mySheet = new Sheet();
                mySheet.setAllLine(allLine);
                sheets.add(mySheet);
            }

            excel.setSheets(sheets);
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        } finally {
            if (book != null){
                book.close();
            }

            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excel;
    }

    private void checkExcel(List<Line> lines){
        int lastLineLength = 0;
        int index = 0;
        for (Line line : lines){
            index ++;
            int contentSize = 0;
            for (String cell : line.getCells()){
                if (!StringUtils.isEmpty(cell)){
                    contentSize ++;
                }
            }
            int currentLineLength = line.getCells().length;
            if (contentSize != 1 || (currentLineLength - lastLineLength) > 1){
                throw new RuntimeException("excel不合法("+ index +"行)");
            }
            lastLineLength = currentLineLength;
        }
    }

    class Tree<T> {
        private T value;
        private Tree<T> parent;
        private List<Tree<T>> child = new ArrayList<>();

        public void setParent(Tree<T> parent) {
            this.parent = parent;
        }

        public Tree<T> getParent() {
            return parent;
        }

        public T getValue() {
            return value;
        }

        public List<Tree<T>> getChild() {
            return child;
        }

        private Tree(T value) {
            this.value = value;
        }

        public void addChild(Tree<T> node){
            child.add(node);
        }
    }

    public class Excel{
        private List<Sheet> sheets;

        public List<Sheet> getSheets() {
            return sheets;
        }

        public void setSheets(List<Sheet> sheets) {
            this.sheets = sheets;
        }
        public void write(){

        }
    }
    public class Sheet{
        private List<Line> allLine;

        public List<Line> getAllLine() {
            return allLine;
        }

        public void setAllLine(List<Line> allLine) {
            this.allLine = allLine;
        }

    }

    public class Line{
        private String cells[];

        public Line(String[] cells){
            this.cells = cells;
        }

        public Line(Cell[] data){
            cells = new String[data.length];
            for (int i = 0; i < data.length ; i ++){
                cells[i] = data[i].getContents();
            }
        }

        public String[] getCells() {
            return cells;
        }
    }
}
