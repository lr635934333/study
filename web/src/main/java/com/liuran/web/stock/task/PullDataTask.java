package com.liuran.web.stock.task;

import com.liuran.web.stock.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PullDataTask {
    private static final String cron = "0 0/1 9-16 * * ?";

    @Autowired
    private StockDataService service;

    @Scheduled(cron = cron)
    public void schedule(){
        Set<String> codes = new HashSet<>();
        codes.add("sz000938");
        codes.add("sh601006");
        codes.add("sh201000");
        codes.add("sh201001");
        codes.add("sh510610");
        codes.add("sh510610");
        codes.add("sh511290");
        codes.add("sh600601");
        synchronized (PullDataTask.class){
            service.pull(codes);
        }
    }
}
