package pl.spring.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="books")
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String imageSrc;
    private double price;
    @DBRef
    private Publisher publisher = new Publisher();

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
