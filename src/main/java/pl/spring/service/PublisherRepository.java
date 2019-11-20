package pl.spring.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import pl.spring.models.Publisher;

@Service
public interface PublisherRepository extends MongoRepository<Publisher, String> {

}
