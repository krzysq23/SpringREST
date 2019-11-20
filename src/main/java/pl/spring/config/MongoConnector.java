package pl.spring.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoConnector {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Log log = LogFactory.getLog(MongoConnector.class);

    public void connect() {
        MongoClient mongo = new MongoClient( "192.168.56.101" , 27017 );
        MongoDatabase database = mongo.getDatabase("library");
        database.createCollection("authors");
        // database.createCollection("books");
        MongoCollection<Document> collection = database.getCollection("authors");
    }

    public void cratedConnections() {
        if(!mongoTemplate.getCollectionNames().contains("books")) {
            mongoTemplate.createCollection("books");
        }
        if(!mongoTemplate.getCollectionNames().contains("publisher")) {
            mongoTemplate.createCollection("publisher");
        }
        if(!mongoTemplate.getCollectionNames().contains("user")) {
            mongoTemplate.createCollection("user");
        }
    }

}
