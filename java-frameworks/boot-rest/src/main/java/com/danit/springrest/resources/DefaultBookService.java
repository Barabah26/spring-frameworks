package com.danit.springrest.resources;

import com.danit.springrest.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService{
    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void create(Book book) {
        bookRepository.create(book);
    }
}
