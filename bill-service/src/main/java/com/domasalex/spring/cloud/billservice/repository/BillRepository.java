package com.domasalex.spring.cloud.billservice.repository;

import com.domasalex.spring.cloud.billservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
