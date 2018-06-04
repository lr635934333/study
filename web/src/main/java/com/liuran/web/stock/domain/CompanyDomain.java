package com.liuran.web.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_company")
public class CompanyDomain {
    @Id
    private String id;
    @Column(name = "name_zn")
    private String nameZn;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "create_time")
    private long createTime;
    @Column(name = "update_time")
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameZn() {
        return nameZn;
    }

    public void setNameZn(String nameZn) {
        this.nameZn = nameZn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
