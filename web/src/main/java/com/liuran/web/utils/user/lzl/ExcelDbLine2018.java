package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleArith;
import com.liuran.web.utils.DoubleUtils;
import jxl.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExcelDbLine2018 extends ExcelLine{

    private double bcjsxfze;
    private double jrybbxje;
    private double ybbxje;
    private double jrmzbxje;
    private double jzjehj;
    private double mzyljzje;
    private double fpjkjzje;
    private double xjzfje;

    public ExcelDbLine2018(Cell[] row){
        if (row == null || row.length == 0){
            return;
        }

        cardId = row[toIndex("G")].getContents();
        name = row[toIndex("H")].getContents();
        sex = row[toIndex("I")].getContents();

        bcjsxfze = DoubleUtils.parseDouble(row[toIndex("X")].getContents());
        jrybbxje = DoubleUtils.parseDouble(row[toIndex("Y")].getContents());
        ybbxje = DoubleUtils.parseDouble(row[toIndex("Z")].getContents());
        jrmzbxje = DoubleUtils.parseDouble(row[toIndex("AA")].getContents());
        jzjehj = DoubleUtils.parseDouble(row[toIndex("AB")].getContents());
        mzyljzje = DoubleUtils.parseDouble(row[toIndex("AC")].getContents());
        fpjkjzje = DoubleUtils.parseDouble(row[toIndex("AD")].getContents());
        xjzfje = DoubleUtils.parseDouble(row[toIndex("AE")].getContents());
    }
    @Override
    List<String> getLine() {
        List<String> list = new ArrayList<>();
        list.add("" + bcjsxfze);
        list.add("" + jrybbxje);
        list.add("" + ybbxje);
        list.add("" + jrmzbxje);
        list.add("" + jzjehj);
        list.add("" + mzyljzje);
        list.add("" + fpjkjzje);
        list.add("" + xjzfje);
        return list;
    }

    @Override
    ExcelLine add(ExcelLine line) {
        ExcelDbLine2018 data = (ExcelDbLine2018) line;
        bcjsxfze = DoubleArith.add(bcjsxfze ,data.getBcjsxfze());
        jrybbxje = DoubleArith.add(jrybbxje , data.getJrybbxje());
        ybbxje = DoubleArith.add(ybbxje , data.getYbbxje());
        jrmzbxje = DoubleArith.add(jrmzbxje , data.getJrmzbxje());
        jzjehj = DoubleArith.add(jzjehj  , data.getJzjehj());
        mzyljzje = DoubleArith.add(mzyljzje , data.getMzyljzje());
        fpjkjzje = DoubleArith.add(fpjkjzje , data.getFpjkjzje());
        xjzfje = DoubleArith.add(xjzfje , data.getXjzfje());

        return this;
    }

    @Override
    List<String> getHeader() {
        return null;
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

    public double getJrmzbxje() {
        return jrmzbxje;
    }

    public void setJrmzbxje(double jrmzbxje) {
        this.jrmzbxje = jrmzbxje;
    }

    public double getJzjehj() {
        return jzjehj;
    }

    public void setJzjehj(double jzjehj) {
        this.jzjehj = jzjehj;
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

    public double getXjzfje() {
        return xjzfje;
    }

    public void setXjzfje(double xjzfje) {
        this.xjzfje = xjzfje;
    }
}
