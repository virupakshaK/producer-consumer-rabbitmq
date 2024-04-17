/**
 * 
 */
package com.veeru.producer.configurations;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Virupaksha K
 *
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class MQConfiguration {

	String host;

	int port;

	String username;

	String password;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Declarables directBindings() {
		Queue directQueue1 = new Queue(Constants.queue6, false);
		DirectExchange directExchange = new DirectExchange(Constants.exchange1);
		return new Declarables(directQueue1, directExchange,
				BindingBuilder.bind(directQueue1).to(directExchange).with(Constants.routingKey3));
	}

	@Bean
	public Declarables topicBindings() {
		Queue topicQueue1 = new Queue(Constants.queue4, false);
		Queue topicQueue2 = new Queue(Constants.queue5, false);
		TopicExchange topicExchange = new TopicExchange(Constants.exchange2);
		return new Declarables(topicQueue1, topicQueue2, topicExchange,
				BindingBuilder.bind(topicQueue1).to(topicExchange).with(Constants.routingKey1),
				BindingBuilder.bind(topicQueue2).to(topicExchange).with(Constants.routingKey2));
	}

	@Bean
	public Declarables fanoutBindings() {

		Queue fanoutQueue1 = new Queue(Constants.queue1, false);
		Queue fanoutQueue2 = new Queue(Constants.queue2, false);
		Queue fanoutQueue3 = new Queue(Constants.queue3, false);

		FanoutExchange fanoutExchange = new FanoutExchange(Constants.exchange3);

		return new Declarables(fanoutQueue1, fanoutQueue2, fanoutQueue3, fanoutExchange,
				BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
				BindingBuilder.bind(fanoutQueue2).to(fanoutExchange),
				BindingBuilder.bind(fanoutQueue3).to(fanoutExchange));
	}

	@Bean
	public SimpleRabbitListenerContainerFactory listenerContainer() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(rabbitConnectionFactory());
		factory.setMaxConcurrentConsumers(5);
		factory.setMessageConverter((MessageConverter) jsonMessageConverter());
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}

	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setHost(host);
		cachingConnectionFactory.setPort(port);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}
}
