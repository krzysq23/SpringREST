package pl.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "pl.spring.service")
public class MongoClientConfiguration extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "library";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://192.168.56.101:27017/");
    }
    
    @Override
    public String getMappingBasePackage() {
        return "pl.spring";
    }
    
	/*
	 * Sessions are not supported by the MongoDB cluster to which this client is
	 * connected 
	 */
//    @Bean
//    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
    
}