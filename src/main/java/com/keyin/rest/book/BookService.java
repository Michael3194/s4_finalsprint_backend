package com.keyin.rest.book;

import com.keyin.domain.Author;
import com.keyin.domain.Book;
import com.keyin.domain.Genre;
import com.keyin.rest.author.AuthorRepository;
import com.keyin.rest.genre.GenreRepository;
import com.keyin.rest.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Book createBook(String title, long authorId, long genreId, Long pageCount) {
        // Check if the author exists
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("Author with ID " + authorId + " not found");
        }
        Author author = authorOptional.get();

        // Check if the genre exists
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isEmpty()) {
            throw new IllegalArgumentException("Genre with ID " + genreId + " not found");
        }
        Genre genre = genreOptional.get();

        // Create the book object
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setGenre(genre);
        newBook.setPageCount(pageCount);
        newBook.setAuthorId(author.getId());
        newBook.setGenreId(genre.getId());

        // Save the book to the database
        return bookRepository.save(newBook);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }


    public Book updateBookById(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        // Update the book fields based on the request
        book.setTitle(bookRequest.getTitle());
        book.setPageCount(bookRequest.getPageCount());
        book.setAuthorId(bookRequest.getAuthorId());
        book.setGenreId(bookRequest.getGenreId());

        // Update the author and genre if provided
        if (bookRequest.getAuthorId() != null) {
            Optional<Author> authorOptional = authorRepository.findById(bookRequest.getAuthorId());
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                book.setAuthor(author);
            } else {
                throw new ResourceNotFoundException("Author not found with ID: " + bookRequest.getAuthorId());
            }
        }

        if (bookRequest.getGenreId() != null) {
            Optional<Genre> genreOptional = genreRepository.findById(bookRequest.getGenreId());
            if (genreOptional.isPresent()) {
                Genre genre = genreOptional.get();
                book.setGenre(genre);
            } else {
                throw new ResourceNotFoundException("Genre not found with ID: " + bookRequest.getGenreId());
            }
        }

        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
