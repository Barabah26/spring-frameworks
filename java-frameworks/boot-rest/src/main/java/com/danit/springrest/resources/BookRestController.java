package com.danit.springrest.resources;

import com.danit.springrest.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Slf4j
//@CrossOrigin(origins = {"http://localhost:3000"})
public class BookRestController {
    @Value("${welcome.message}")
    private String message;

    @Autowired
    private BookService bookService;


    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(1L,"Java 8", "Horstman"),
            new Book(2L, "Java 17", "Horstman, Kornell")
    ));

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isPresent()){
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.badRequest().body(String.format("Book is not found by {id}"));
        }
    }

    @Operation(summary = "Get a book by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/{bookName}")
    public ResponseEntity<?> getByName(@PathVariable String bookName) {
        if (StringUtils.isEmpty(bookName)) {
            log.error("Book name can't be empty");
            return ResponseEntity.badRequest().body("Book name can't be empty");
        }

        List<Book> result = books.stream().filter(b -> bookName.equals(b.getName())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(result)) {
            log.warn("No books with such name");
            return ResponseEntity.badRequest().body("No books with such name");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public void addNewBook(@RequestBody Book book){
        bookService.create(book);
    }

}
