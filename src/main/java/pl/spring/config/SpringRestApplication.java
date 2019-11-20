package pl.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication()
@ComponentScan(basePackages = { "pl.spring.config", "pl.spring.controller", "pl.spring.service", "pl.spring.security"} )
public class SpringRestApplication {

	@Autowired
	private MongoConnector mongoConnector;

	@PostConstruct
	public void init() {
		mongoConnector.cratedConnections();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
