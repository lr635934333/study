package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleUtils;
import jxl.Cell;

public class ExcelDbLine2016 extends ExcelDbLine2015{

    public ExcelDbLine2016(Cell[] row) {
        cardId = row[toIndex("F")].getContents();
        name = row[toIndex("G")].getContents();
        sex = row[toIndex("H")].getContents();

        bcjsxfze = DoubleUtils.parseDouble(row[toIndex("T")].getContents());
        jrybbxje = DoubleUtils.parseDouble(row[toIndex("U")].getContents());
        ybbxje = DoubleUtils.parseDouble(row[toIndex("V")].getContents());
        jzhjje = DoubleUtils.parseDouble(row[toIndex("W")].getContents());
        mzyljzje = DoubleUtils.parseDouble(row[toIndex("X")].getContents());
        fpjkjzje = DoubleUtils.parseDouble(row[toIndex("Y")].getContents());
        zfjje = DoubleUtils.parseDouble(row[toIndex("Z")].getContents());
    }
}
