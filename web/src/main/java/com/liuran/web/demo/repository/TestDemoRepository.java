package com.liuran.web.demo.repository;

import com.liuran.web.demo.domain.TestDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TestDemoRepository extends JpaRepository<TestDemo, String> {
}
