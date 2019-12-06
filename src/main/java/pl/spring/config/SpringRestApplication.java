package pl.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		  MongoAutoConfiguration.class, 
		  MongoDataAutoConfiguration.class
		})
@ComponentScan(basePackages = { "pl.spring.config", "pl.spring.controller", "pl.spring.service", "pl.spring.security"} )
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
