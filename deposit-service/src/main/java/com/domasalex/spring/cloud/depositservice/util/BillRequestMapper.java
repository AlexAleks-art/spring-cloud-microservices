package com.domasalex.spring.cloud.depositservice.util;

import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillRequestDto;
import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillRequestMapper {

    public BillRequestDto createBillRequestDto(BigDecimal amount, BillResponseDto billResponseDto) {
        BillRequestDto billRequestDto = new BillRequestDto();
        billRequestDto.setAccountId(billResponseDto.getAccountId());
        billRequestDto.setCreatedDate(billResponseDto.getCreatedDate());
        billRequestDto.setIsDefault(billResponseDto.getIsDefault());
        billRequestDto.setOverdraftEnabled(billResponseDto.getOverdraftEnabled());
        billRequestDto.setAmount(billResponseDto.getAmount().add(amount));
        return billRequestDto;
    }

}
