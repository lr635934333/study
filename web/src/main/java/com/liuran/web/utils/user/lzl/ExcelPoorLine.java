package com.liuran.web.utils.user.lzl;

import jxl.Cell;

import java.util.List;

public class ExcelPoorLine extends ExcelLine{

    public ExcelPoorLine(Cell[] row){
        this.cardId = row[toIndex("I")].getContents();
    }

    @Override
    List<String> getLine() {
        return null;
    }

    @Override
    ExcelLine add(ExcelLine line) {
        return null;
    }

    @Override
    List<String> getHeader() {
        return null;
    }
}
