/**
 * 
 */
package com.veeru.consumer.listeners;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.Channel;
import com.veeru.consumer.configuration.Constants;
import com.veeru.consumer.utility.APIUtilities;

/**
 * @author Virupaksha K
 *
 */
@Component
public class DirectMessageListners {

	Logger log = LoggerFactory.getLogger(DirectMessageListners.class);

	@RabbitListener(queues = Constants.queue6)
	public void directExchangeListener1(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Direct-Exchange -> queue-6 Listner-1:");

		String msg = APIUtilities.toStringValue(message);
		// do further process here
		log.info("data: {}", msg);
	}

	@RabbitListener(queues = Constants.queue6)
	public void directExchangeListener2(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Direct-Exchange -> queue-6 Listner-2:");
		String msg = APIUtilities.toStringValue(message);
		// do further process here
		log.info("data: {}", msg);
	}
}
