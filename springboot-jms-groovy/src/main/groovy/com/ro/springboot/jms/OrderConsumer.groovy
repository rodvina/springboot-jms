package com.ro.springboot.jms;

import static com.ro.springboot.AppConfig.ORDER_QUEUE;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * https://memorynotfound.com/spring-jms-setting-reading-header-properties-example/
 * @author rodneyodvina
 *
 */
@Component
public class OrderConsumer {
	private static Logger log = LoggerFactory.getLogger(OrderConsumer.class);
	
	

	/**
	 * This method demonstrates multiple ways to access JMS Message Headers
	 * @param order
	 * @param correlationId
	 * @param nonExistingHeader
	 * @param headers
	 * @param messageHeaders
	 * @param jmsMessageHeaderAccessor
	 */
	@JmsListener(destination = ORDER_QUEUE)
	public void receiveMessage(@Payload Order order,
					            @Header(JmsHeaders.CORRELATION_ID) String correlationId,
					            @Header(name = "jms-header-not-exists", defaultValue = "default") String nonExistingHeader,
					            @Headers Map<String, Object> headers,
					            MessageHeaders messageHeaders,
					            JmsMessageHeaderAccessor jmsMessageHeaderAccessor) {

//		Order order = message.getPayload();
		log.info "received <" + order + ">"
		
		log.info "\n# Spring JMS accessing single header property"
		log.info "- jms_correlationId=" + correlationId
		log.info "- jms-header-not-exists=" + nonExistingHeader
		
		log.info "\n# Spring JMS retrieving all header properties using Map<String, Object>"
		log.info "- jms-custom-header=" + String.valueOf(headers.get("jms-custom-property"))
		
		log.info "\n# Spring JMS retrieving all header properties MessageHeaders"
		log.info "- jms-custom-property-price=" + messageHeaders.get("jms-custom-property-price", Double.class)
		
		log.info "\n# Spring JMS retrieving all header properties JmsMessageHeaderAccessor"
		log.info "- jms_destination=" + jmsMessageHeaderAccessor.getDestination()
		log.info "- jms_priority=" + jmsMessageHeaderAccessor.getPriority()
		log.info "- jms_timestamp=" + jmsMessageHeaderAccessor.getTimestamp()
		log.info "- jms_type=" + jmsMessageHeaderAccessor.getType()
		log.info "- jms_redelivered=" + jmsMessageHeaderAccessor.getRedelivered()
		log.info "- jms_replyTo=" + jmsMessageHeaderAccessor.getReplyTo()
		log.info "- jms_correlationId=" + jmsMessageHeaderAccessor.getCorrelationId()
		log.info "- jms_contentType=" + jmsMessageHeaderAccessor.getContentType()
		log.info "- jms_expiration=" + jmsMessageHeaderAccessor.getExpiration()
		log.info "- jms_messageId=" + jmsMessageHeaderAccessor.getMessageId()
		log.info "- jms_deliveryMode=" + jmsMessageHeaderAccessor.getDeliveryMode() + "\n"
		
		}
}
