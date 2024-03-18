package library.service;

import library.infrastructure.entity.BookEntity;
import library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public BookEntity getOne(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("The book doesn't exist in the database!");
        }
        bookRepository.deleteById(id);
    }
}
