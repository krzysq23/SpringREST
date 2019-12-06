package pl.spring.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="order")
public class Order {

    @Id
    private String id;
    private String userLogin;
    private String payment;
    private String delivery;
    private String adress;
    private String phone;
    private String email;
    private Date dateCreated;
    private String status;
    private double amount;
    @DBRef
    private List<OrderPosition> positions = new ArrayList<OrderPosition>();
    
}
