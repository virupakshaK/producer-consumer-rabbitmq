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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.AMQP.Channel;
import com.veeru.consumer.configuration.Constants;
import com.veeru.consumer.configuration.MQConfiguration;
import com.veeru.consumer.entities.CustomMessage;
import com.veeru.consumer.utility.APIUtilities;

/**
 * @author Virupaksha K
 *
 */
@Configuration
public class NotificationListners {

	Logger log = LoggerFactory.getLogger(NotificationListners.class);

	@Autowired
	MQConfiguration config;

	@RabbitListener(queues = Constants.queue1)
	public void fanoutExchangeListener1(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue1 Listner-1:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue1)
	public void fanoutExchangeListener2(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue1 Listner-2:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue2)
	public void fanoutExchangeListener3(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue-2 Listner-3:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue2)
	public void fanoutExchangeListener4(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue-2 Listner-4:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue3)
	public void fanoutExchangeListener5(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue-3 Listner-5:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue3)
	public void fanoutExchangeListener6(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Fanout Exchange -> queue-3 Listner-6:");
		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

}
