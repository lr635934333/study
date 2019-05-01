package com.liuran.web.utils;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    public static ExcelUtils build(){
        return new ExcelUtils();
    }

    public static int toIndex(String line){
        if (line == null || line.length() < 1){
            return 0;
        }
        line = line.trim().toLowerCase();

        //计算最大的系数
        int base = 1;
        for (int i = 1; i < line.length() ; i ++){
            base = base * 26;
        }

        //计算index
        int index = 0;
        for (int i = 0; i < line.length() ; i ++){
            if (i == line.length() - 1){
                int units = line.charAt(line.length() - 1) - 'a';
                index = index + units;
            } else {
                index = index + ((line.charAt(i) - 'a' + 1) * base);
                base = base / 26;
            }
        }

        return index;
    }

    public static String toExcelColumn(int index){
        String result = null;
        if (index > 25){
            int base = 1;
            while (true){
                if (base * 26 > index){
                    break;
                }
                base = base * 26;
            }
            //商
            int consult = index / base;
            //余数
            int remainder = index % base;

            char consultChar = (char) (consult + 'a' - 1);
            result = new String(new char[]{consultChar});

            String remainderChar = toExcelColumn(remainder);

            result = result + remainderChar;
        } else {
            char c = (char) (index + 'a');
            result = new String(new char[]{c});
        }

        return result.toUpperCase();
    }

    public Excel read(File file){
        Excel excel = new Excel();
        FileInputStream fis = null;
        Workbook book = null;
        try {
            fis = new FileInputStream(file);
            book = Workbook.getWorkbook(fis);
            List<Sheet> sheets = new ArrayList<>();

            for (jxl.Sheet sheet :book.getSheets()){
                int maxWidth = 0;

                List<Line> allLine = new ArrayList<>();
                int length = 0;
                int rows = sheet.getRows();
                for(int i=0; i< rows; i ++){
                    length = sheet.getRow(i).length;
                    Line line = new Line(length, sheet.getRow(i));
                    allLine.add(line);
                    maxWidth = length > maxWidth ? length : maxWidth;
                }

                Sheet mySheet = new Sheet(maxWidth);

                mySheet.setName(sheet.getName());
                mySheet.setAllLine(allLine);
                sheets.add(mySheet);
            }

            excel.setSheets(sheets);
            excel.setName(file.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
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

    public class Excel{
        private List<Sheet> sheets;
        private String name;

        public Excel(){

        }

        public Excel(String name, List<Sheet> sheets){
            this.name = name;
            this.sheets = sheets;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
        private List<Line> data;
        private Line title;
        private String[] excelColumn;
        private String name;
        private int width;

        public Sheet(int width){
            this.width = width;
            excelColumn = new String[width];
            for (int i = 0 ;i <width ; i ++){
                excelColumn[i] = toExcelColumn(i);
            }
        }

        public String[] getExcelColumn() {
            return excelColumn;
        }

        public void setExcelColumn(String[] excelColumn) {
            this.excelColumn = excelColumn;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Line> getData() {
            return data;
        }

        public void setData(List<Line> data) {
            this.data = data;
        }

        public Line getTitle() {
            return title;
        }

        public void setTitle(Line title) {
            this.title = title;
        }

        public List<Line> getAllLine() {
            return allLine;
        }

        public void setAllLine(List<Line> allLine) {
            this.allLine = allLine;
        }

        public Sheet subSheet(String[] columns){
            Sheet sheet = new Sheet(columns.length);

            List<Line> newAllLines = new ArrayList<>();
            for (Line line : allLine){
                Line newLine = line.subLine(columns);
                newAllLines.add(newLine);
            }
            sheet.setAllLine(newAllLines);

            if (title != null){
                Line newTitle = title.subLine(columns);
                sheet.setTitle(newTitle);
            }


            if (data != null){
                List<Line> newData = new ArrayList<>();
                for (Line line : data){
                    newData.add(line.subLine(columns));
                }

                sheet.setData(newData);
            }

            return sheet;
        }

        public void resolveTitle(int titleLine){
            resolveTitle(titleLine, titleLine + 1);
        }

        public void resolveTitle(int titleLine, int dataStart){
            title = allLine.get(titleLine);
            for (int i = dataStart ;i < allLine.size() ; i ++){
                if (data == null){
                    data = new ArrayList<>();
                }

                data.add(allLine.get(i));
            }
        }

        public void resolveTitle(int titleLine, int dataStart, int dataEnd){
            title = allLine.get(titleLine);
            for (int i = dataStart ;i <= dataEnd ; i ++){
                if (data == null){
                    data = new ArrayList<>();
                }

                data.add(allLine.get(i));
            }
        }

        public Sheet doing(Operation operation){
            return operation.doing(this);
        }
    }

    public class Line{
        private String cells[];

        public Line(String[] cells){
            this.cells = cells;
        }

        public Line(int length, Cell[] data){
            cells = new String[length];
            for (int i = 0; i < data.length ; i ++){
                if (i == length){
                    break;
                }

                cells[i] = data[i].getContents();
            }
        }

        public Line(String[] columns, Cell[] data){
            cells = new String[columns.length];

            for (int i = 0; i < columns.length ; i ++){
                String column = columns[i];
                cells[i] = data[toIndex(column)].getContents();
            }
        }

        public Line subLine(String[] columns){
            String[] subCells = new String[columns.length];

            int subCellIndex = 0;
            for (String column : columns){
                int index = toIndex(column);
                if (index > cells.length - 1){
                    continue;
                }
                subCells[subCellIndex] = cells[toIndex(column)];
                subCellIndex ++;
            }

            return new Line(subCells);
        }


        public String[] getCells() {
            return cells;
        }

        public String get(String index){
            return cells[toIndex(index)];
        }

        public String get(int index){
            return cells[index];
        }
    }

    public interface Operation{
        Sheet doing(Sheet sheet);
    }

    public static void main(String[] args) {
        String result = toExcelColumn(toIndex("AAA"));


        Excel excel = ExcelUtils.build().read(new File("/Users/liuran/Desktop/temp/普通住院和重大疾病数据.xls"));
        for (Sheet sheet : excel.getSheets()){
            //分离标题
            sheet.resolveTitle(0);

            String[] columns = {"C","A","I","J","K","L","M","N","O","P"};
            Sheet subSheet = null;
            if (sheet.getName().equals("2015")){
                subSheet = sheet.subSheet(columns);
                subSheet.doing(new Operation() {
                    @Override
                    public Sheet doing(Sheet sheet) {
                        Map<String, List<Line>> map = new HashMap<>();
                        for (Line line : sheet.getData()){
                            if (map.containsKey(line.get("A"))){
                                List<Line> list = map.get(line.get("A"));
                                list.add(line);
                            } else {
                                List<Line> list = new ArrayList<>();
                                map.put(line.get("A"), list);
                                list.add(line);
                            }
                        }
                     return sheet;
                    }
                });
            }

            System.out.println("");
        }
        return;
    }

}
