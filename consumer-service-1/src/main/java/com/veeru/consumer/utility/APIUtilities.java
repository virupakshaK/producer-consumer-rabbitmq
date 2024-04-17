/**
 * 
 */
package com.veeru.consumer.utility;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veeru.consumer.entities.CustomMessage;

/**
 * @author Virupaksha K
 *
 */
public class APIUtilities {
	private static Logger log = LoggerFactory.getLogger(APIUtilities.class);
	private final static ObjectMapper mapper = new ObjectMapper();

	public static CustomMessage toObject(Message message) throws IOException, ClassNotFoundException {
		try {
			return mapper.readValue(new String(message.getBody()), CustomMessage.class);
		} catch (JsonMappingException e) {
			log.error("Exception:" + e.getStackTrace());
		} catch (JsonProcessingException e) {
			log.error("Exception:" + e.getStackTrace());
		}
		return null;

	}
	
	public static String toStringValue(Message message) {
		try {
			return mapper.readValue(new String(message.getBody()), String.class);
		} catch (JsonMappingException e) {
			log.error("Exception:" + e.getStackTrace());
		} catch (JsonProcessingException e) {
			log.error("Exception:" + e.getStackTrace());
		}
		return null;

	}
}
