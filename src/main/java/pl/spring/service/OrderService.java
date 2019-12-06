package pl.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Transactional
	public void saveOrder(Order order) {
		
		for (OrderPosition position : order.getPositions()) {
			orderPositionRepository.save(position);
		}
		orderRepository.save(order);
	}

}
