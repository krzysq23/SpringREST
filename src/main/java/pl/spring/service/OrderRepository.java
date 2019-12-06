package pl.spring.service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import pl.spring.models.Order;

@Service
public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByUserLogin(String status);

}
