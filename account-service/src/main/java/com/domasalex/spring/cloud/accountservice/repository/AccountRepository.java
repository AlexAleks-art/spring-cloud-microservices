package com.domasalex.spring.cloud.accountservice.repository;

import com.domasalex.spring.cloud.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
