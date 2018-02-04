package com.ro.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
//@EnableJms
public class AppConfig {
	public static final String ORDER_QUEUE = "order-queue"
	
	@Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter()
        converter.setTargetType MessageType.TEXT
        converter.setTypeIdPropertyName "_type"
        return converter
    }
}
