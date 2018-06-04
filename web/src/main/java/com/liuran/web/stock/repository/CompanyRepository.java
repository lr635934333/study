package com.liuran.web.stock.repository;

import com.liuran.web.stock.domain.CompanyDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDomain, String>{

}
