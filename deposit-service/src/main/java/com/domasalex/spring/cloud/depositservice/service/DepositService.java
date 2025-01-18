package com.domasalex.spring.cloud.depositservice.service;

import com.domasalex.spring.cloud.depositservice.dto.DepositRequestDTO;
import com.domasalex.spring.cloud.depositservice.dto.DepositResponseDTO;
import com.domasalex.spring.cloud.depositservice.entity.Deposit;
import com.domasalex.spring.cloud.depositservice.exception.DepositServiceException;
import com.domasalex.spring.cloud.depositservice.repository.DepositRepository;
import com.domasalex.spring.cloud.depositservice.rest.AccountServiceClient;
import com.domasalex.spring.cloud.depositservice.rest.BillServiceClient;
import com.domasalex.spring.cloud.depositservice.rest.dto.account.AccountResponseDTO;
import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillRequestDto;
import com.domasalex.spring.cloud.depositservice.rest.dto.bill.BillResponseDto;
import com.domasalex.spring.cloud.depositservice.util.BillRequestMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class DepositService {

    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";

    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;

    private final RabbitTemplate rabbitTemplate;

    private final BillRequestMapper billRequestMapper;

    @Autowired
    public DepositService(DepositRepository depositRepository, AccountServiceClient accountServiceClient, BillServiceClient billServiceClient, RabbitTemplate rabbitTemplate, BillRequestMapper billRequestMapper) {
        this.depositRepository = depositRepository;
        this.accountServiceClient = accountServiceClient;
        this.billServiceClient = billServiceClient;
        this.rabbitTemplate = rabbitTemplate;
        this.billRequestMapper = billRequestMapper;
    }

    public DepositResponseDTO deposit(Long accountId, Long billId, BigDecimal amount) {
        if(accountId == null && billId == null) {
            throw new DepositServiceException("Account is null and bill is null");
        }
        if(billId != null){
            BillResponseDto billResponseDto = billServiceClient.getBillById(billId);
            BillRequestDto billRequestDto = billRequestMapper.createBillRequestDto(amount, billResponseDto);
            billServiceClient.update(billId, billRequestDto);

            AccountResponseDTO accountResponseDTO = accountServiceClient.getAccountById(billResponseDto.getAccountId());
            depositRepository.save(new Deposit(amount, billId, OffsetDateTime.now(), accountResponseDTO.getEmail()));
            return createResponse(amount, accountResponseDTO);
        }
        BillResponseDto defaultBill = getDefaultBill(accountId);
        BillRequestDto billRequestDto = billRequestMapper.createBillRequestDto(amount, defaultBill);
        billServiceClient.update(defaultBill.getBillId(), billRequestDto);
        AccountResponseDTO accountResponseDTO = accountServiceClient.getAccountById(accountId);
        depositRepository.save(new Deposit(amount, defaultBill.getBillId(), OffsetDateTime.now(), accountResponseDTO.getEmail()));
        return createResponse(amount, accountResponseDTO);
    }

    private BillResponseDto getDefaultBill(Long accountId){
        return billServiceClient
                .getBillsByAccountId(accountId)
                .stream()
                .filter(BillResponseDto::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill"));
    }

    private DepositResponseDTO createResponse(BigDecimal amount, AccountResponseDTO accountResponseDTO){
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(amount, accountResponseDTO.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, objectMapper.writeValueAsString(depositResponseDTO));
        }catch(JsonProcessingException exception){
            exception.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDTO;
    }


}
