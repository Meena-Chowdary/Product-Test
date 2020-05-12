package com.cts;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableCircuitBreaker
@EnableFeignClients
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
	
//	 @Bean
//	   public RestTemplate restTemplate(RestTemplateBuilder builder) {
//	      return builder.build();
//	   }
	
	@Bean
	Docket configureSwagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/products/**"))
				.build()
				
				.apiInfo(new ApiInfo("Product API Documentation",
						"A Sample API for Products",
						"1.0.0",
						"Shopping is Fun",
						new Contact("Mahesh", "www.mahesh.com", "mahesh@gmail.com"), 
						"Standard API License",
						"www.maheshuma.com",
						Collections.emptyList()));
}

}
