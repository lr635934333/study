package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleArith;
import com.liuran.web.utils.DoubleUtils;
import jxl.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExcelDbLine2015 extends ExcelLine{

    double bcjsxfze;
    double jrybbxje;
    double ybbxje;
    double jzhjje;
    double mzyljzje;
    double fpjkjzje;
    double zfjje;

    public ExcelDbLine2015(){

    }

    public ExcelDbLine2015(Cell[] row){
        if (row == null || row.length == 0){
            return;
        }
        cardId = row[toIndex("G")].getContents();
        name = row[toIndex("H")].getContents();
        sex = row[toIndex("I")].getContents();

        bcjsxfze = DoubleUtils.parseDouble(row[toIndex("U")].getContents());
        jrybbxje = DoubleUtils.parseDouble(row[toIndex("V")].getContents());
        ybbxje = DoubleUtils.parseDouble(row[toIndex("W")].getContents());
        jzhjje = DoubleUtils.parseDouble(row[toIndex("X")].getContents());
        mzyljzje = DoubleUtils.parseDouble(row[toIndex("Y")].getContents());
        fpjkjzje = DoubleUtils.parseDouble(row[toIndex("Z")].getContents());
        zfjje = DoubleUtils.parseDouble(row[toIndex("AA")].getContents());
    }

    public double getBcjsxfze() {
        return bcjsxfze;
    }

    public void setBcjsxfze(double bcjsxfze) {
        this.bcjsxfze = bcjsxfze;
    }

    public double getJrybbxje() {
        return jrybbxje;
    }

    public void setJrybbxje(double jrybbxje) {
        this.jrybbxje = jrybbxje;
    }

    public double getYbbxje() {
        return ybbxje;
    }

    public void setYbbxje(double ybbxje) {
        this.ybbxje = ybbxje;
    }

    public double getJzhjje() {
        return jzhjje;
    }

    public void setJzhjje(double jzhjje) {
        this.jzhjje = jzhjje;
    }

    public double getMzyljzje() {
        return mzyljzje;
    }

    public void setMzyljzje(double mzyljzje) {
        this.mzyljzje = mzyljzje;
    }

    public double getFpjkjzje() {
        return fpjkjzje;
    }

    public void setFpjkjzje(double fpjkjzje) {
        this.fpjkjzje = fpjkjzje;
    }

    public double getZfjje() {
        return zfjje;
    }

    public void setZfjje(double zfjje) {
        this.zfjje = zfjje;
    }

    @Override
    List<String> getLine() {
        List<String> list = new ArrayList<>();
        list.add("" + bcjsxfze);
        list.add("" + jrybbxje);
        list.add("" + ybbxje);
        list.add("" + jzhjje);
        list.add("" + mzyljzje);
        list.add("" + fpjkjzje);
        list.add("" + zfjje);
        return list;
    }

    @Override
    ExcelLine add(ExcelLine line) {
        ExcelDbLine2015 data = (ExcelDbLine2015) line;
        bcjsxfze = DoubleArith.add(bcjsxfze ,data.getBcjsxfze());
        jrybbxje = DoubleArith.add(jrybbxje , data.getJrybbxje());
        ybbxje = DoubleArith.add(ybbxje , data.getYbbxje());
        jzhjje = DoubleArith.add(jzhjje , data.getJzhjje());
        mzyljzje = DoubleArith.add(mzyljzje , data.getMzyljzje());
        fpjkjzje = DoubleArith.add(fpjkjzje , data.getFpjkjzje());
        zfjje = DoubleArith.add(zfjje , data.getZfjje());

        return this;
    }

    @Override
    List<String> getHeader() {
        return null;
    }
}
