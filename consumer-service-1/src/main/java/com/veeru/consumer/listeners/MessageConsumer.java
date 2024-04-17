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
import com.veeru.consumer.entities.CustomMessage;
import com.veeru.consumer.utility.APIUtilities;

/**
 * @author Virupaksha K
 *
 */
@Component
public class MessageConsumer {
	Logger log = LoggerFactory.getLogger(MessageConsumer.class);

	@RabbitListener(queues = Constants.queue4)
	public void topicExchangeListener1(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Topic Exchange -> queue4 Listner-1:");

		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue4)
	public void topicExchangeListener2(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Topic Exchange -> queue4 Listner-2:");

		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue5)
	public void topicExchangeListener3(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Topic Exchange -> queue5 Listner-3:");

		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

	@RabbitListener(queues = Constants.queue5)
	public void topicExchangeListener4(final Message message, Channel channel,
			@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws ClassNotFoundException, IOException {
		log.info("Topic Exchange -> queue5 Listner-4:");

		CustomMessage custMsg = APIUtilities.toObject(message);
		// do your further process here
		log.info("data:{}", custMsg);
	}

}
