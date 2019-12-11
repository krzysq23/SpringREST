package pl.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.models.Book;
import pl.spring.models.Order;
import pl.spring.models.OrderPosition;
import pl.spring.models.StatusOrderEnum;
import pl.spring.service.BookRepository;
import pl.spring.service.OrderRepository;
import pl.spring.service.OrderService;

@RestController
@RequestMapping("api")
public class OrderRestController {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping(value = "/getAllOrdersByUser/{user}", produces = "application/json")
    public List<Order> getAllOrders(@PathVariable String user) {
        return orderRepository.findByUserLogin(user);
    }

    @GetMapping(value = "/findOrderById/{id}", produces = "application/json")
    public Order findOrderById(@PathVariable String id) {
        return orderRepository.findById(id).get();
    }

    @PostMapping(path = "/saveOrder", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveOrder(@RequestBody String json) {
    	
        try {
        	JSONObject obj = new JSONObject(json);
        	String payment = obj.getString("payment"), delivery = obj.getString("delivery"), 
        			adress = obj.getString("adress"), phone = obj.getString("phone"), 
        					email = obj.getString("email"), login = obj.getString("login");
        	double amount = obj.getDouble("amount");
        	

        	List<OrderPosition> positions = new ArrayList<OrderPosition>();
        	
        	JSONArray arr = obj.getJSONArray("books");
        	for (int i = 0; i < arr.length(); i++) {
        	    Book book = bookRepository.findById(arr.getJSONObject(i).getString("id")).get();
        	    OrderPosition position = new OrderPosition(null, arr.getJSONObject(i).getInt("qty"), book);
        	    positions.add(position);
        	    
        	}
        	
        	Order order = new Order(null, login, payment, delivery, adress, phone, email, 
        			new Date(), StatusOrderEnum.OCZEKUJACY_NA_PLATNOSC.toString(), amount, positions );
        	
        	orderService.saveOrder(order);
        	
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Zamówienie zostało dodane");
    }


    @PostMapping(path = "/payForOrder", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> payForOrder(@RequestBody String json) {
    	
        try {
        	JSONObject obj = new JSONObject(json);
        	String orderId = obj.getString("orderId");
        	
        	orderService.updateStatus(orderId, StatusOrderEnum.ZREALIZOWANY.toString());
        	
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Status zamówienia został zmieniony");
    }
    
}