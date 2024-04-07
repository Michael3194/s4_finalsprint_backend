package com.keyin.rest.genre;

import com.keyin.domain.Author;
import com.keyin.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/genre")
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        try {
            Genre createdGenre = genreService.createGenre(genre);
            return ResponseEntity.ok(createdGenre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create Genre: " + e.getMessage());
        }
    }

    @GetMapping("/genre")
    public Iterable<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable long id) {
        try {
            Genre genre = genreService.getGenreById(id);
            return ResponseEntity.ok().body(genre);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
