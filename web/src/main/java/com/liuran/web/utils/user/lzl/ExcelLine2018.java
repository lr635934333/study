package com.liuran.web.utils.user.lzl;

import com.liuran.web.utils.DoubleArith;
import com.liuran.web.utils.DoubleUtils;
import jxl.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExcelLine2018 extends ExcelLine{
    public double fyze;
    public double kbxje;
    public double tcbx;
    public double zfeije;
    public double zfuje;
    public double cxjje;
    public double mzde;
    public double mzdedyj;
    public double xjzf;
    public double debx;
    public double ybzebx;
    public double ycfbz;
    public double mzjz;
    public double ysfwfbxbz;
    public double ysfwf;
    public double dbzyljgdz;

    public ExcelLine2018(){}

    public ExcelLine2018(String cardId, String name){
        this.cardId = cardId;
        this.name = name;
    }

    public ExcelLine2018(Cell[] row){
        this.cardId = row[3].getContents();
        this.name = row[4].getContents();
        this.sex = row[toIndex("F")].getContents();
        this.fyze = DoubleUtils.parseDouble(row[9].getContents());
        this.kbxje = DoubleUtils.parseDouble(row[10].getContents());
        this.tcbx = DoubleUtils.parseDouble(row[11].getContents());
        this.zfeije = DoubleUtils.parseDouble(row[12].getContents());
        this.zfuje = DoubleUtils.parseDouble(row[13].getContents());
        this.cxjje = DoubleUtils.parseDouble(row[14].getContents());
        this.mzde = DoubleUtils.parseDouble(row[15].getContents());
        this.mzdedyj = DoubleUtils.parseDouble(row[16].getContents());
        this.xjzf = DoubleUtils.parseDouble(row[17].getContents());
        this.debx = DoubleUtils.parseDouble(row[18].getContents());
        this.ybzebx = DoubleUtils.parseDouble(row[19].getContents());
        this.ycfbz = DoubleUtils.parseDouble(row[20].getContents());
        this.mzjz = DoubleUtils.parseDouble(row[21].getContents());
        this.ysfwfbxbz = DoubleUtils.parseDouble(row[22].getContents());
        this.ysfwf = DoubleUtils.parseDouble(row[23].getContents());
        this.dbzyljgdz = DoubleUtils.parseDouble(row[24].getContents());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

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

    public double getMzde() {
        return mzde;
    }

    public void setMzde(double mzde) {
        this.mzde = mzde;
    }

    public double getMzdedyj() {
        return mzdedyj;
    }

    public void setMzdedyj(double mzdedyj) {
        this.mzdedyj = mzdedyj;
    }

    public double getXjzf() {
        return xjzf;
    }

    public void setXjzf(double xjzf) {
        this.xjzf = xjzf;
    }

    public double getDebx() {
        return debx;
    }

    public void setDebx(double debx) {
        this.debx = debx;
    }

    public double getYbzebx() {
        return ybzebx;
    }

    public void setYbzebx(double ybzebx) {
        this.ybzebx = ybzebx;
    }

    public double getYcfbz() {
        return ycfbz;
    }

    public void setYcfbz(double ycfbz) {
        this.ycfbz = ycfbz;
    }

    public double getMzjz() {
        return mzjz;
    }

    public void setMzjz(double mzjz) {
        this.mzjz = mzjz;
    }

    public double getYsfwfbxbz() {
        return ysfwfbxbz;
    }

    public void setYsfwfbxbz(double ysfwfbxbz) {
        this.ysfwfbxbz = ysfwfbxbz;
    }

    public double getYsfwf() {
        return ysfwf;
    }

    public void setYsfwf(double ysfwf) {
        this.ysfwf = ysfwf;
    }

    public double getDbzyljgdz() {
        return dbzyljgdz;
    }

    public void setDbzyljgdz(double dbzyljgdz) {
        this.dbzyljgdz = dbzyljgdz;
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
        list.add("" + zfeije);
        list.add("" + zfuje);
        list.add("" + cxjje);
        list.add("" + mzde);
        list.add("" + mzdedyj);
        list.add("" + xjzf);
        list.add("" + debx);
        list.add("" + ybzebx);
        list.add("" + ycfbz);
        list.add("" + mzjz);
        list.add("" + ysfwfbxbz);
        list.add("" + ysfwf);
        list.add("" + dbzyljgdz);

        return list;
    }

    @Override
    ExcelLine add(ExcelLine line) {
        ExcelLine2018 data = (ExcelLine2018) line;

        fyze = DoubleArith.add(fyze , data.getFyze());
        kbxje = DoubleArith.add(kbxje , data.getKbxje());
        tcbx = DoubleArith.add(tcbx , data.getTcbx());
        zfeije = DoubleArith.add(zfeije , data.getZfeije());
        zfuje = DoubleArith.add(zfuje , data.getZfuje());
        cxjje = DoubleArith.add(cxjje , data.getCxjje());
        mzde = DoubleArith.add(mzde ,data.getMzde());
        mzdedyj = DoubleArith.add(mzdedyj ,data.getMzdedyj());
        xjzf = DoubleArith.add(xjzf , data.getXjzf());
        debx = DoubleArith.add(debx , data.getDebx());
        ybzebx = DoubleArith.add(ybzebx , data.getYbzebx());
        ycfbz = DoubleArith.add(ycfbz , data.getYcfbz());
        mzjz = DoubleArith.add(mzjz ,data.getMzjz());
        ysfwfbxbz = DoubleArith.add(ysfwfbxbz , data.getYsfwfbxbz());
        ysfwf = DoubleArith.add(ysfwf , data.getYsfwf());
        dbzyljgdz = DoubleArith.add(dbzyljgdz ,data.getDbzyljgdz());
        return this;
    }

    @Override
    List<String> getHeader() {
        return null;
    }
}
