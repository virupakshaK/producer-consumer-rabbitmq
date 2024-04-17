/**
 * 
 */
package com.veeru.producer.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Virupaksha K
 *
 */
public class CustomMessage implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;
	private String message;
	private Date messageDate;
	private String exchangeType;
	
	
	public CustomMessage() {
	}
	public CustomMessage(String messageId, String message, Date messageDate, String exchangeType) {
		this.messageId = messageId;
		this.message = message;
		this.messageDate = messageDate;
		this.exchangeType = exchangeType;
	}
	public String getMessageId() {
		return messageId;
	}
	public String getMessage() {
		return message;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public String getExchangeType() {
		return exchangeType;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}
	
	@Override
	public String toString() {
		return "CustomMessage [messageId=" + messageId + ", message=" + message + ", messageDate=" + messageDate
				+ ", exchangeType=" + exchangeType + "]";
	}

	

}
