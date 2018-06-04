package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleUtils;
import jxl.Cell;

public class ExcelLine2017 extends ExcelLine2018{

    public ExcelLine2017(String cardId, String name){
        super(cardId, name);
    }

    public ExcelLine2017(Cell[] row) {
        this.cardId = row[3].getContents();
        this.name = row[4].getContents();
        this.sex = row[toIndex("F")].getContents();
        this.fyze = DoubleUtils.parseDouble(row[8].getContents());
        this.kbxje = DoubleUtils.parseDouble(row[9].getContents());
        this.tcbx = DoubleUtils.parseDouble(row[10].getContents());
        this.zfeije = DoubleUtils.parseDouble(row[11].getContents());
        this.zfuje = DoubleUtils.parseDouble(row[12].getContents());
        this.cxjje = DoubleUtils.parseDouble(row[13].getContents());
        this.mzde = DoubleUtils.parseDouble(row[14].getContents());
        this.mzdedyj = DoubleUtils.parseDouble(row[15].getContents());
        this.xjzf = DoubleUtils.parseDouble(row[16].getContents());
        this.debx = DoubleUtils.parseDouble(row[17].getContents());
        this.ybzebx = DoubleUtils.parseDouble(row[18].getContents());
        this.ycfbz = DoubleUtils.parseDouble(row[19].getContents());
        this.mzjz = DoubleUtils.parseDouble(row[20].getContents());
        this.ysfwfbxbz = DoubleUtils.parseDouble(row[21].getContents());
        this.ysfwf = DoubleUtils.parseDouble(row[22].getContents());
        this.dbzyljgdz = DoubleUtils.parseDouble(row[23].getContents());
    }
}
