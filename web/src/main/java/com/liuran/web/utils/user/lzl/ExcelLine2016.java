package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleArith;
import jxl.Cell;
import com.liuran.web.utils.DoubleUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelLine2016 extends ExcelLine{

    public double fyze;
    public double kbxje;
    public double tcbx;
    public double ysfwf;
    public double zfeije;
    public double zfuje;
    public double cxjje;
    public double xjzf;
    public double dae;
    public double mzjz;


    public double getFyze() {
        return fyze;
    }

    public void setFyze(double fyze) {
        this.fyze = fyze;
    }

    public double getKbxje() {
        return kbxje;
    }

    public void setKbxje(double kbxje) {
        this.kbxje = kbxje;
    }

    public double getTcbx() {
        return tcbx;
    }

    public void setTcbx(double tcbx) {
        this.tcbx = tcbx;
    }

    public double getYsfwf() {
        return ysfwf;
    }

    public void setYsfwf(double ysfwf) {
        this.ysfwf = ysfwf;
    }

    public double getZfeije() {
        return zfeije;
    }

    public void setZfeije(double zfeije) {
        this.zfeije = zfeije;
    }

    public double getZfuje() {
        return zfuje;
    }

    public void setZfuje(double zfuje) {
        this.zfuje = zfuje;
    }

    public double getCxjje() {
        return cxjje;
    }

    public void setCxjje(double cxjje) {
        this.cxjje = cxjje;
    }

    public double getXjzf() {
        return xjzf;
    }

    public void setXjzf(double xjzf) {
        this.xjzf = xjzf;
    }

    public double getDae() {
        return dae;
    }

    public void setDae(double dae) {
        this.dae = dae;
    }

    public double getMzjz() {
        return mzjz;
    }

    public void setMzjz(double mzjz) {
        this.mzjz = mzjz;
    }

    public ExcelLine2016(String cardId, String name){
        this.cardId = cardId;
        this.name = name;
    }

    public ExcelLine2016(Cell[] row){
        this.cardId = row[2].getContents();
        this.name = row[0].getContents();
        this.sex = row[toIndex("B")].getContents();
        this.fyze = DoubleUtils.parseDouble(row[8].getContents());
        this.kbxje = DoubleUtils.parseDouble(row[9].getContents());
        this.tcbx = DoubleUtils.parseDouble(row[10].getContents());
        this.ysfwf = DoubleUtils.parseDouble(row[11].getContents());
        this.zfeije = DoubleUtils.parseDouble(row[12].getContents());
        this.zfuje = DoubleUtils.parseDouble(row[13].getContents());
        this.cxjje = DoubleUtils.parseDouble(row[14].getContents());
        this.xjzf = DoubleUtils.parseDouble(row[15].getContents());
        this.dae = DoubleUtils.parseDouble(row[16].getContents());
        this.mzjz = DoubleUtils.parseDouble(row[17].getContents());
    }

    @Override
    public List<String> getLine() {
        List<String> list = new ArrayList<>();
        list.add(cardId);
        list.add(name);
        list.add(sex);
        list.add("" + fyze);
        list.add("" + kbxje);
        list.add("" + tcbx);
        list.add("" + ysfwf);
        list.add("" + zfeije);
        list.add("" + zfuje);
        list.add("" + cxjje);
        list.add("" + xjzf);
        list.add("" + dae);
        list.add("" + mzjz);
        return list;
    }

    @Override
    ExcelLine add(ExcelLine line) {

        ExcelLine2016 date = (ExcelLine2016) line;
        fyze = DoubleArith.add(fyze ,date.getFyze());
        kbxje = DoubleArith.add(kbxje , date.getKbxje());
        tcbx = DoubleArith.add(tcbx , date.getTcbx());
        ysfwf = DoubleArith.add(ysfwf , date.getYsfwf());
        zfeije = DoubleArith.add(zfeije  , date.getZfeije());
        zfuje = DoubleArith.add(zfuje , date.getZfuje());
        cxjje = DoubleArith.add(cxjje , date.getCxjje());
        xjzf = DoubleArith.add(xjzf , date.getXjzf());
        dae = DoubleArith.add(dae , date.getDae());
        mzjz = DoubleArith.add(mzjz , date.getMzjz());

        return this;
    }

    @Override
    List<String> getHeader() {
        List<String> list = new ArrayList<>();

        return list;
    }
}
