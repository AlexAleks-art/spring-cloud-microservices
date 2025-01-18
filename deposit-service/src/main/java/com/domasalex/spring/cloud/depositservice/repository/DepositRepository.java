package com.domasalex.spring.cloud.depositservice.repository;

import com.domasalex.spring.cloud.depositservice.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
