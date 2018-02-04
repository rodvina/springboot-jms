package com.ro.springboot;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ro.springboot.jms.Order;
import com.ro.springboot.jms.OrderSender;

@SpringBootApplication
public class SpringbootJmsJavaApplication implements ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(SpringbootJmsJavaApplication.class);

	@Autowired
	private OrderSender orderSender;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJmsJavaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Setting and Reading Spring JMS Message Header Properties Example");

        orderSender.sendQueue(new Order("me", "you", new BigDecimal(12)));

        log.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3);
        System.exit(-1);
		
	}
}
