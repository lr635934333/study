package com.liuran.web.utils.user.lzl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "作坊";
        Map<String, List<ExcelLine>> zhuYuanData = ExcelUtil.readExcel("/Users/liuran/Desktop/temp/普通住院和重大疾病数据.xls", 1);
        Map<String, List<ExcelLine>> zhuYuanDataSum = sum(zhuYuanData);

        Map<String, List<ExcelLine>> zdjbDate = ExcelUtil.readExcel("/Users/liuran/Desktop/temp/新津村大病.xls", 2);
        Map<String, List<ExcelLine>> zzdjbDateSum = sum(zdjbDate);

        Map<String, List<ExcelLine>> poorPeople = ExcelUtil.readExcel("/Users/liuran/Desktop/temp/新津村贫困户信息.xls", 3);
        Set<String> poor = new HashSet<>();
        for (ExcelLine line : poorPeople.get("POOR")){
            poor.add(line.getCardId());
        }

        Map<String, List<List<String>>> outPutData = new TreeMap<>();

        String year = "2015";
        outPutData.put(year, merge(zhuYuanDataSum.get(year), zzdjbDateSum.get(year), poor));
        year = "2016";
        outPutData.put(year, merge(zhuYuanDataSum.get(year), zzdjbDateSum.get(year), poor));
        year = "2017";
        outPutData.put(year, merge(zhuYuanDataSum.get(year), zzdjbDateSum.get(year), poor));
        year = "2018";
        outPutData.put(year, merge(zhuYuanDataSum.get(year), zzdjbDateSum.get(year), poor));

        ExcelUtil.writeExcel("/Users/liuran/Desktop/temp/output.xls", outPutData);
        return;
    }

    public static List<List<String>> merge(List<ExcelLine> zhuYuan, List<ExcelLine> zzdjb, Set<String> poor){
        Map<String, ExcelLine> zzdjbMap = new HashMap<>();
        int zhuYuanLength = zhuYuan.get(0).getLine().size();
        int zzdjbLength = zzdjb.get(0).getLine().size();

        List<List<String>> result = new LinkedList<>();
        for (ExcelLine excelLine : zzdjb){
            zzdjbMap.put(excelLine.getCardId(), excelLine);
        }

        for (ExcelLine zhuYuanLine : zhuYuan){
            List<String> mergeExcelLine = new ArrayList<>();
            mergeExcelLine.addAll(zhuYuanLine.getLine());
            mergeExcelLine.add("#");
            if (zzdjbMap.containsKey(zhuYuanLine.getCardId())){
                ExcelLine zzdjbLine = zzdjbMap.get(zhuYuanLine.getCardId());
                mergeExcelLine.addAll(zzdjbLine.getLine());
                zzdjbMap.remove(zhuYuanLine.getCardId());
            } else {
                for (int i = 0; i < zzdjbLength ;i ++){
                    mergeExcelLine.add("");
                }
            }

            if (poor.contains(zhuYuanLine.getCardId())){
                mergeExcelLine.add("是");
            } else {
                mergeExcelLine.add("否");
            }

            result.add(mergeExcelLine);
        }

        for (ExcelLine zzdjbLine : zzdjbMap.values()){
            if (zzdjbLine.getCardId() == null || zzdjbLine.getCardId().length() < 10){
                continue;
            }
            List<String> mergeExcelLine = new LinkedList<>();
            mergeExcelLine.add(zzdjbLine.getCardId());
            mergeExcelLine.add(zzdjbLine.getName());
            mergeExcelLine.add(zzdjbLine.getSex());
            for (int i = 0;i < zhuYuanLength - 3 ;i ++){
                mergeExcelLine.add("");
            }

            mergeExcelLine.add("#");

            mergeExcelLine.addAll(zzdjbLine.getLine());

            if (poor.contains(zzdjbLine.getCardId())){
                mergeExcelLine.add("是");
            } else {
                mergeExcelLine.add("否");
            }

            result.add(mergeExcelLine);
        }

        return result;

    }


    public static Map<String, List<ExcelLine>> sum(Map<String, List<ExcelLine>> data){
        Map<String, List<ExcelLine>> zhuYuanData = new TreeMap<>();

        for (String sheetName : data.keySet()){
            List<ExcelLine> lines = data.get(sheetName);
            Map<String, ExcelLine> map = new HashMap<>();
            for (ExcelLine excelLine : lines){
                if (map.containsKey(excelLine.getCardId())){
                    ExcelLine old = map.get(excelLine.getCardId());
                    old.add(excelLine);
                } else {
                    map.put(excelLine.getCardId(), excelLine);
                }
            }

            zhuYuanData.put(sheetName, new ArrayList<ExcelLine>(map.values()));

        }

        return zhuYuanData;
    }
}
