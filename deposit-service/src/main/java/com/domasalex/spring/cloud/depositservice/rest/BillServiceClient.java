package com.domasalex.spring.cloud.depositservice.rest;

import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillRequestDto;
import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.GET)
    BillResponseDto getBillById(@PathVariable("billId") Long billId);

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.PUT)
    void update(@PathVariable("billId") Long billId, @RequestBody BillRequestDto billRequestDto);

    @RequestMapping(value = "bills/account/{accountId}", method = RequestMethod.GET)
    List<BillResponseDto> getBillsByAccountId(@PathVariable("accountId") Long accountId);
}
