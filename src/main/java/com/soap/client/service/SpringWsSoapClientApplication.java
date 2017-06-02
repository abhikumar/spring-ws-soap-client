package com.soap.client.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.soap.type.Sum;
import com.soap.type.SumResponse;

@SpringBootApplication
public class SpringWsSoapClientApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringWsSoapClientApplication.class, args);

		System.out.println("######################## Service Started ===============================");
		WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
		Sum sum = new Sum();
		sum.setArg0(1);
		sum.setArg1(5);
		SumResponse response = (SumResponse) webServiceTemplate.marshalSendAndReceive(sum);
		System.out.println("SumResponse : " + response.getReturn());
	}
}
