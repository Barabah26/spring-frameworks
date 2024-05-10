package com.danit.springrest.model;

import lombok.Data;

@Data
public class Book {
    private final Long id;
    private final String name;
    private final String author;
}
