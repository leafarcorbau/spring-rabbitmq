package com.dh.sp.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${dh.rabbitmq.queue}")
    private String queueName;

    @Value("${dh.rabbitmq.exchange}")
    private String exchange;

    @Bean
    public Queue gdprQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public FanoutExchange gdprExchange() {
        return new FanoutExchange(exchange, true, false);
    }

    @Bean
    public Binding gdprQueueToGdprExchangeBinding(final Queue gdprQueue,final FanoutExchange gdprExchange) {
        return BindingBuilder.bind(gdprQueue).to(gdprExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
