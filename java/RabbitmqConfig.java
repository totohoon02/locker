package com.redismailer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_NAME = "mail-queue";
    public static final String EXCHANGE_NAME = "mail-exchange";
    public static final String ROUTING_KEY = "mail.send";

    // 1. 큐 생성
    @Bean
    public Queue mailQueue() {
        // durable: RabbitMQ가 재시작되어도 큐가 유지됨
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    // 2. 익스체인지 생성 (Direct)
    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 3. 바인딩 (큐와 익스체인지 연결)
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // 4. JSON 변환기 (객체를 JSON으로 자동 변환)
    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    // 5. RabbitTemplate 설정
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
