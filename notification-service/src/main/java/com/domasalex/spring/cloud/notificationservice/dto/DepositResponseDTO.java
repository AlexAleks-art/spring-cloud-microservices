package com.domasalex.spring.cloud.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositResponseDTO {

    private BigDecimal amount;

    private String mail;

}
