package com.danit.springrest.resources;

import com.danit.springrest.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();
    Optional<Book> findById(Long id);
    void create(Book book);
}
