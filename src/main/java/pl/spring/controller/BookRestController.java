package pl.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.models.Book;
import pl.spring.models.Publisher;
import pl.spring.service.BookRepository;
import pl.spring.service.PublisherRepository;

@RestController
@RequestMapping("api")
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @GetMapping(value = "/findByIsbn-{isbn}", produces = "application/json")
    public Book findByTitle(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @GetMapping(value = "/findBookById-{id}", produces = "application/json")
    public Book findById(@PathVariable String id) {
        return bookRepository.findById(id).get();
    }

    @GetMapping(value = "/findPublisherById-{id}", produces = "application/json")
    public Publisher findPublisherById(@PathVariable String id) {
        return publisherRepository.findById(id).get();
    }

    @PostMapping(path = "/addBook", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            publisherRepository.save(book.getPublisher());
            bookRepository.save(book);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Dodano książkę");
    }

    @PostMapping(path = "/addPublisher", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addBook(@RequestBody Publisher publisher) {
        try {
            publisherRepository.save(publisher);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Dodano wydawcę");
    }

    @PostMapping(path = "/editBook", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        if(bookRepository.findById(book.getId()) != null) {
            publisherRepository.save(book.getPublisher());
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/removeBook/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Usunięto", HttpStatus.OK);
    }

}