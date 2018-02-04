package com.ro.springboot.jms;

import static com.ro.springboot.AppConfig.ORDER_QUEUE;

import java.util.UUID;

import javax.jms.DeliveryMode;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {
	private static Logger log = LoggerFactory.getLogger(OrderSender.class);
	
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendQueue(Order order) {
        log.info("sending with convertAndSend() to " + ORDER_QUEUE + " <" + order + ">");
        
//anonymous inner class converted to lamda expression
//        jmsTemplate.convertAndSend(ORDER_QUEUE, order, new MessagePostProcessor() {
//
//			@Override
//			public Message postProcessMessage(Message m) throws JMSException {
//				log.info("setting standard JMS headers before sending");
//	            m.setJMSCorrelationID(UUID.randomUUID().toString());
//	            m.setJMSExpiration(1000);
//	            m.setJMSMessageID("message-id");
//	            m.setJMSDestination(new ActiveMQQueue(ORDER_QUEUE));
//	            m.setJMSReplyTo(new ActiveMQQueue(ORDER_QUEUE));
//	            m.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
//	            m.setJMSPriority(Message.DEFAULT_PRIORITY);
//	            m.setJMSTimestamp(System.nanoTime());
//	            m.setJMSType("type");
//
//	            log.info("setting custom JMS headers before sending");
//	            m.setStringProperty("jms-custom-header", "this is a custom jms property");
//	            m.setBooleanProperty("jms-custom-property", true);
//	            m.setDoubleProperty("jms-custom-property-price", 0.0);
//
//	            return m;
//			}});
        
        //send to order-queue, order dto converted to json, with headers
        jmsTemplate.convertAndSend(ORDER_QUEUE, order, m -> {

            log.info("setting standard JMS headers before sending");
            m.setJMSCorrelationID(UUID.randomUUID().toString());
            m.setJMSExpiration(1000);
            m.setJMSMessageID("message-id");
            m.setJMSDestination(new ActiveMQQueue(ORDER_QUEUE));
            m.setJMSReplyTo(new ActiveMQQueue(ORDER_QUEUE));
            m.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            m.setJMSPriority(Message.DEFAULT_PRIORITY);
            m.setJMSTimestamp(System.nanoTime());
            m.setJMSType("type");

            log.info("setting custom JMS headers before sending");
            m.setStringProperty("jms-custom-header", "this is a custom jms property");
            m.setBooleanProperty("jms-custom-property", true);
            m.setDoubleProperty("jms-custom-property-price", 5.0);

            return m;
        });

    }
}
