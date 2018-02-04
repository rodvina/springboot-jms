package com.ro.springboot

import java.util.concurrent.TimeUnit

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import com.ro.springboot.jms.Order
import com.ro.springboot.jms.OrderSender


@SpringBootApplication
class SpringbootJmsApplication implements ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(SpringbootJmsApplication.class);
	
	@Autowired
	private OrderSender orderSender;
	
	static void main(String[] args) {
		SpringApplication.run SpringbootJmsApplication, args
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Setting and Reading Spring JMS Message Header Properties Example");

        orderSender.sendQueue(new Order(from: "me", to: "you", amount: new BigDecimal(12)));

        log.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3);
        System.exit(-1);	
	}
}
