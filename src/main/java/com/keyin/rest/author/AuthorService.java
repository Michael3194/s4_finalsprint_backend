package com.keyin.rest.author;

import com.keyin.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // GET AUTHOR BY ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    // GET ALL AUTHORS
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // CREATE AUTHOR
    public Author createAuthor(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    // UPDATE AUTHOR BY ID
    public Author updateAuthorById(long id, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        existingAuthor.setFirstName(updatedAuthor.getFirstName());
        existingAuthor.setLastName(updatedAuthor.getLastName());

        return authorRepository.save(existingAuthor);
    }

    // DELETE AUTHOR BY ID
    public void deleteAuthorById(long id) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        authorRepository.delete(existingAuthor);
    }

    // DELETE ALL AUTHORS
    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }
}
