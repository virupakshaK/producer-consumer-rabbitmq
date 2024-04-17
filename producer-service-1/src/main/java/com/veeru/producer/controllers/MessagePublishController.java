/**
 * 
 */
package com.veeru.producer.controllers;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veeru.producer.configurations.Constants;
import com.veeru.producer.configurations.MQConfiguration;
import com.veeru.producer.entities.CustomMessage;

/**
 * @author Virupaksha K
 *
 */
@RestController
@RequestMapping("/message")
public class MessagePublishController {
	Logger log = LoggerFactory.getLogger(MessagePublishController.class);
	@Autowired
	RabbitTemplate template;

	@Autowired
	MQConfiguration config;

	/**
	 * REST API to publish message to listeners through appropriate queues.
	 * 
	 * @param message
	 * @return Object type is ResponseEntity<String>
	 */

	@GetMapping("/publishToDirect/{message}")
	public ResponseEntity<String> publishDirectMessage(@PathVariable String message) {
		template.convertAndSend(Constants.exchange1, Constants.routingKey3, message);
		return new ResponseEntity<>("Published message to queue through Direct Exchange.", HttpStatus.OK);
	}

	@PostMapping("/publishToTopic")
	public ResponseEntity<String> publishTopicMessage(@RequestBody CustomMessage message) {

		try {
			message.setMessageId(UUID.randomUUID().toString());
			message.setMessageDate(new Date());

			String exchangeType = message.getExchangeType();

			Function<String, String> fun = (e) -> {

				if (!e.isEmpty() && Constants.exchange2.equalsIgnoreCase(e)) {
					template.convertAndSend(Constants.exchange2, Constants.routingKey1, message);
					template.convertAndSend(Constants.exchange2, Constants.routingKey2, message);
				} else {
					log.info("Sending it to Default Exchange.");
					template.convertAndSend(message);
				}
				return "published message";
			};

			return new ResponseEntity<>(fun.apply(exchangeType), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception:" + e.getStackTrace());
			return new ResponseEntity<>("Exception Occured while publishing message." + message.getMessage(),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * REST API to notify message to all intended listeners
	 * 
	 * @param message
	 * @return Object type is ResponseEntity<String>
	 */

	@PostMapping("/publishNotification")
	public ResponseEntity<String> BroadCastNotification(@RequestBody CustomMessage message) {
		try {
			message.setMessageId(UUID.randomUUID().toString());
			message.setMessageDate(new Date());
			template.convertAndSend(Constants.exchange3, "", message);

			return new ResponseEntity<>("published notification successfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception:" + e.getStackTrace());
			return new ResponseEntity<>("Exception Occured while publishing notification:" + message.getMessage(),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
}
