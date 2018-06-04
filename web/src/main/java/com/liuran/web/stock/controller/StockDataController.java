package com.liuran.web.stock.controller;

import com.liuran.web.stock.domain.StockDataDomain;
import com.liuran.web.stock.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v0.1/stock/data")
public class StockDataController {
    @Autowired
    private StockDataService service;

    @RequestMapping(value = "pull", method = RequestMethod.POST)
    public List<StockDataDomain> pull(@RequestBody Map<String, Object> param){
        List<String> code = (List<String>) param.get("code");
        return service.pull(new HashSet<>(code));
    }
}
