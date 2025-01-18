package com.domasalex.spring.cloud.depositservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequestDTO {

    private Long accountId;

    private Long billId;

    private BigDecimal amount;

}
