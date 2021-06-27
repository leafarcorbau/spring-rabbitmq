package com.dh.sp.rabbit.service;

import com.dh.sp.rabbit.msg.GdprMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GdprPublisher {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${dh.rabbitmq.exchange}")
    private String exchange;

    public void send(final GdprMessage gdprMessage) {
        rabbitTemplate.convertAndSend(exchange, "", gdprMessage);
        log.info("Send msg = {}", gdprMessage);

    }
}
