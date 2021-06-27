package com.dh.sp.rabbit.service;

import com.dh.sp.rabbit.IntegrationTest;
import com.dh.sp.rabbit.msg.GdprMessage;
import com.dh.sp.rabbit.msg.TestGdprMessage;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.UUID;

@SpringBootTest
public class GdprPublisherIntegrationTest extends IntegrationTest {

    @Autowired
    private GdprPublisher gdprPublisher;

    @Test
    public void shouldSendGdprMessage(){
        //Given
        final GdprMessage gdprMessage = TestGdprMessage.getInstance(UUID.randomUUID()).build();

        //Then
        gdprPublisher.send(gdprMessage);

        Duration timeout = Duration.ofSeconds(3);
        Awaitility.await().atLeast(timeout).atMost(timeout);
    }
}
