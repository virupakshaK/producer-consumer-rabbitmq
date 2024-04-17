/**
 * 
 */
package com.veeru.producer.entities;

/**
 * @author Virupaksha K
 *
 */

public class ChannelInfo {

	int id;
	String exchange;
	String queue;
	String routingkey;

	public ChannelInfo() {
	}

	public ChannelInfo(int id, String exchange, String queue, String routingkey) {
		this.id = id;
		this.exchange = exchange;
		this.queue = queue;
		this.routingkey = routingkey;
	}

	public int getId() {
		return id;
	}

	public String getExchange() {
		return exchange;
	}

	public String getQueue() {
		return queue;
	}

	public String getRoutingkey() {
		return routingkey;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public void setRoutingkey(String routingkey) {
		this.routingkey = routingkey;
	}

}
