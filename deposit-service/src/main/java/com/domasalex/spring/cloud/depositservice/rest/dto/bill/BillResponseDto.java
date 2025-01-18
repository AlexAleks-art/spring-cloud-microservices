package com.domasalex.spring.cloud.depositservice.rest.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BillResponseDto {

    private Long billId;

    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private OffsetDateTime createdDate;

    private Boolean overdraftEnabled;

}
