package pl.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.models.Order;
import pl.spring.models.OrderPosition;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
    @Autowired
    private OrderPositionRepository orderPositionRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Transactional
	public void saveOrder(Order order) {
		
		for (OrderPosition position : order.getPositions()) {
			orderPositionRepository.save(position);
		}
		orderRepository.save(order);
	}
    
    @Transactional
   	public void updateStatus(String orderId, String status) {
   		
    	Query query = new Query(Criteria.where("id").is(orderId));
        Update update = Update
                    .update("status", status);

        mongoTemplate.findAndModify(query, update, Order.class);
   	}
    

}
