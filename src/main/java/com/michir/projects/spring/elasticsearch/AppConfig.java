package com.michir.projects.spring.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig implements DisposableBean {

	private Client client;
	
	@Bean
	public Client client() throws UnknownHostException {
		TransportClient client = TransportClient.builder().build()
		        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		return client;
	}

	@Override
	public void destroy() throws Exception {
		client.close();
	}
	
}
