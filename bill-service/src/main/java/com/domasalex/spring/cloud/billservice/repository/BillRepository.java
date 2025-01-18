package com.domasalex.spring.cloud.billservice.repository;

import com.domasalex.spring.cloud.billservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> getBillsByAccountId(Long accountId);

}
