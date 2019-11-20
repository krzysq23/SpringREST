package pl.spring.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import pl.spring.models.Book;
import java.util.List;

@Service
public interface BookRepository extends MongoRepository<Book, String> {

    public Book findByIsbn(String isbn);
    public List<Book> findByAuthor(String author);

}
