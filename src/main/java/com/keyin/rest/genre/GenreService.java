package com.keyin.rest.genre;

import com.keyin.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // GET GENRE BY ID
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

    // GET ALL GENRES
    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // CREATE GENRE
    public Genre createGenre(Genre newGenre) {
        return genreRepository.save(newGenre);
    }


}
