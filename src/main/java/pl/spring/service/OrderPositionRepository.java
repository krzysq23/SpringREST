package pl.spring.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import pl.spring.models.OrderPosition;

@Service
public interface OrderPositionRepository extends MongoRepository<OrderPosition, String> {


}
