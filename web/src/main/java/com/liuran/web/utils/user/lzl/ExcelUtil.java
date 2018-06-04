package com.liuran.web.utils.user.lzl;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.*;
import java.util.*;

public class ExcelUtil {
    public static int ONE = 1;
    public static int TWO = 2;
    public static int POOR = 3;
    public static Map<String, List<ExcelLine>> readExcel(String fileName, int type){
        Map<String, List<ExcelLine>> result = new HashMap<>();
        File file = new File(fileName);
        FileInputStream fis = null;
        Workbook book = null;
        try {
            fis = new FileInputStream(file);
            book = Workbook.getWorkbook(fis);

            for (Sheet sheet :book.getSheets()){
                String sheetName = sheet.getName();
                List<ExcelLine> lineList = new ArrayList<>();
                for(int i=0; i< sheet.getRows(); i ++){
                    if (i == 0){
                        continue;
                    }
                    Cell[] rowData = sheet.getRow(i);

                    if (ONE == type){
                        if (sheetName.contains("2016")){
                            lineList.add(new ExcelLine2016(rowData));
                        } else if (sheetName.contains("2017")){
                            lineList.add(new ExcelLine2017(rowData));
                        } else if (sheetName.contains("2018")){
                            lineList.add(new ExcelLine2018(rowData));
                        } else if (sheetName.contains("2015")){
                            lineList.add(new ExcelLine2015(rowData));
                        }
                    } else if (TWO == type){
                        if (sheetName.contains("2016")){
                            lineList.add(new ExcelDbLine2016(rowData));
                        } else if (sheetName.contains("2017")){
                            lineList.add(new ExcelDbLine2017(rowData));
                        } else if (sheetName.contains("2018")){
                            lineList.add(new ExcelDbLine2018(rowData));
                        } else if (sheetName.contains("2015")){
                            lineList.add(new ExcelDbLine2015(rowData));
                        }
                    } else if (POOR == type){
                        if (sheetName.equals("POOR")){
                            lineList.add(new ExcelPoorLine(rowData));
                        }
                    }

                }

                result.put(sheetName, lineList);
            }

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
        return result;
    }
    public static void writeExcel(String fileName, Map<String, List<List<String>>> data){

        File file = new File(fileName);
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(file);
            int page = 0;
            for (String sheetName : data.keySet()){
                WritableSheet sheet = book.createSheet(sheetName, page);
                List<List<String>> lines = data.get(sheetName);
                for (int i =0 ; i < lines.size() ; i ++){
                    int j = 0;
                    for (Object obj : lines.get(i)){
                        try{
                            sheet.addCell(new Label(j, i, obj.toString()));
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        j ++;
                    }
                }
                page ++;
            }

            book.write();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (book != null){
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
