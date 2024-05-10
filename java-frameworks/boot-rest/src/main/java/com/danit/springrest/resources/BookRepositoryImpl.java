package com.danit.springrest.resources;

import com.danit.springrest.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(1L, "Java 8", "Horstman"),
            new Book(2L, "Java 17", "Horstman, Kornell")
    ));

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public void create(Book book) {
        books.add(book);
    }
}
