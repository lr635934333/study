package com.liuran.web.stock.repository;

import com.liuran.web.stock.domain.StockDataDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDataRepository extends JpaRepository<StockDataDomain, String>{
    public StockDataDomain findByTimeAndStockCode(long time, String code);
}
