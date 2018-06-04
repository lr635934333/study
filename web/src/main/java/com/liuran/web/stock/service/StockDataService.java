package com.liuran.web.stock.service;

import com.liuran.web.stock.util.StockUtils;
import com.liuran.web.stock.domain.StockDataDomain;
import com.liuran.web.stock.repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StockDataService {

    @Autowired
    private StockDataRepository repository;

    public List<StockDataDomain> pull(Set<String> code){
        List<StockDataDomain> result = new ArrayList<>();

        List<StockDataDomain> datas = StockUtils.getFormSina(code);
        for (StockDataDomain stockDataDomain : datas){
            stockDataDomain.setId(UUID.randomUUID().toString());
            stockDataDomain.setCreateTime(System.currentTimeMillis());

            StockDataDomain old = repository.findByTimeAndStockCode(stockDataDomain.getTime(),
                    stockDataDomain.getStockCode());
            if (old != null){
                result.add(old);
            } else {
                result.add(repository.save(stockDataDomain));
            }
        }

        return result;
    }
}
