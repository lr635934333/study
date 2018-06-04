package com.liuran.web.demo.service;

import com.liuran.web.demo.domain.TestDemo;
import com.liuran.web.demo.repository.TestDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestService {

    @Autowired
    private TestDemoRepository repository;

    public TestDemo testService(TestDemo demo){
        demo.setId(UUID.randomUUID().toString());

        demo.setCreateTime(System.currentTimeMillis());
        demo.setUpdateTime(System.currentTimeMillis());

        return repository.save(demo);
    }

    public TestDemo get(String id){
        return repository.findById(id).orElse(new TestDemo());
    }
}
