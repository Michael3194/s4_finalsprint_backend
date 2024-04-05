package com.keyin.rest.author;

import com.keyin.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id) {
        try {
            Author author = authorService.getAuthorById(id);
            return ResponseEntity.ok().body(author);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @GetMapping("/author")
    public Iterable<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        try {
            Author createdAuthor = authorService.createAuthor(author);
            return ResponseEntity.ok(createdAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create Author: " + e.getMessage());
        }
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable long id, @RequestBody Author updatedAuthor) {
        try {
            Author author = authorService.updateAuthorById(id, updatedAuthor);
            return ResponseEntity.ok().body(author);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
