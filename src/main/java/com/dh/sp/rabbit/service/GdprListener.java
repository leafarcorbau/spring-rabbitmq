package com.dh.sp.rabbit.service;

import com.dh.sp.rabbit.msg.GdprMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GdprListener {

    @RabbitListener(queues = "${dh.rabbitmq.queue}")
    public void process(final GdprMessage gdprMessage) {
        log.info("received msg = {}", gdprMessage);
    }
}
