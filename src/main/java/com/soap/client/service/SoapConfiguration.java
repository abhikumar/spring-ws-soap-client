package com.soap.client.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

@Configuration
public class SoapConfiguration {
	@Bean
	public Wss4jSecurityInterceptor securityInterceptor() {
		Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
		wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
		wss4jSecurityInterceptor.setSecurementUsername("admin");
		wss4jSecurityInterceptor.setSecurementPassword("secret");
		wss4jSecurityInterceptor.setValidateResponse(false);
		return wss4jSecurityInterceptor;
	}

	@Bean
	public Jaxb2Marshaller getMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.soap.type");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate serviceTemplate = new WebServiceTemplate();
		serviceTemplate.setMarshaller(getMarshaller());
		serviceTemplate.setUnmarshaller(getMarshaller());
		ClientInterceptor[] interceptors = new ClientInterceptor[] { securityInterceptor() };
		serviceTemplate.setInterceptors(interceptors);
		serviceTemplate.setDefaultUri("http://localhost:8080/ws/MyService");
		return serviceTemplate;
	}

}
