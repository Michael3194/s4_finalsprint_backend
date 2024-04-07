package com.keyin.rest.book;

import com.keyin.domain.Book;
import com.keyin.rest.request.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest request) {
        try {
            Book createdBook = bookService
                    .createBook(request.getTitle(), request.getAuthorId(), request.getGenreId(), request.getPageCount());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
