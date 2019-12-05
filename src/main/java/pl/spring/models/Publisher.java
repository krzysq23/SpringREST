package pl.spring.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection="publisher")
public class Publisher {

    @Id
    private String id;
    private String name;
    private int founded;
    private String location;
}
