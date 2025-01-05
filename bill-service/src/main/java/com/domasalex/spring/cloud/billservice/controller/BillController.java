package com.domasalex.spring.cloud.billservice.controller;

import com.domasalex.spring.cloud.billservice.dto.BillRequestDto;
import com.domasalex.spring.cloud.billservice.dto.BillResponseDto;
import com.domasalex.spring.cloud.billservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public BillResponseDto getBill(@PathVariable Long billId) {
        return new BillResponseDto(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.createBill(billRequestDto.getAccountId(), billRequestDto.getAmount(),billRequestDto.getIsDefault(), billRequestDto.getCreatedDate(), billRequestDto.getOverdraftEnabled());
    }

    @PutMapping("/{billId}")
    public BillResponseDto updateBill(@PathVariable Long billId, @RequestBody BillRequestDto billRequestDto) {
        return new BillResponseDto(billService.updateBill(billId, billRequestDto.getAccountId(), billRequestDto.getAmount(),billRequestDto.getIsDefault(), billRequestDto.getCreatedDate(), billRequestDto.getOverdraftEnabled()));
    }

    @DeleteMapping("/{billId}")
    public BillResponseDto deleteBill(@PathVariable Long billId) {
        return new BillResponseDto(billService.deleteBill(billId));
    }
}
