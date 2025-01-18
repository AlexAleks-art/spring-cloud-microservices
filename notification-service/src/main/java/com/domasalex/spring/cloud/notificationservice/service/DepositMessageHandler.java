package com.domasalex.spring.cloud.notificationservice.service;

import com.domasalex.spring.cloud.notificationservice.config.RabbitMQConfig;
import com.domasalex.spring.cloud.notificationservice.dto.DepositResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//Consumer
@Service
public class DepositMessageHandler {

    private final JavaMailSender javaMailSender;

    @Autowired
    public DepositMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        DepositResponseDTO depositResponseDTO = objectMapper.readValue(jsonBody, DepositResponseDTO.class);
        System.out.println(depositResponseDTO);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(depositResponseDTO.getMail());
        simpleMailMessage.setFrom("aleksej.domas1@gmail.com");

        simpleMailMessage.setSubject("Deposit Message");
        simpleMailMessage.setText("Make deposit, sum: " + depositResponseDTO.getAmount());

        try {
            javaMailSender.send(simpleMailMessage);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
