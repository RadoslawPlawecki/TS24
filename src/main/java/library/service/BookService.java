package library.service;

import library.controller.DTO.BookDTO.CreateBookDTO;
import library.controller.DTO.BookDTO.CreateBookResponseDTO;
import library.controller.DTO.BookDTO.GetBookDTO;
import library.exception.BookNotFound;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDTO> getAll() {
        ArrayList<BookEntity> books = (ArrayList<BookEntity>) bookRepository.findAll();
        return books.stream().map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDTO getOne(int id) {
        var book = bookRepository.findById(id).orElseThrow(() -> BookNotFound.create(id));
        return mapBook(book);
    }

    public CreateBookResponseDTO addBook(CreateBookDTO book) {
        var bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        bookEntity.setPublicationYear(book.getPublicationYear());
        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDTO(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublicationYear(), newBook.getAvailableCopies());
    }

    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        bookRepository.deleteById(id);
    }

    private GetBookDTO mapBook(BookEntity book) {
        return new GetBookDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getAvailableCopies() > 0);
    }
}
