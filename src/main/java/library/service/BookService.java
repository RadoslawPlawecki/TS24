package library.service;

import library.controller.DTO.BookDTO.*;
import library.exception.BookInvalidNumber;
import library.exception.BookNotFound;
import library.exception.PendingReturn;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.RentalEntity;
import library.infrastructure.repository.BookRepository;
import library.infrastructure.repository.RentalRepository;
import library.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public BookService(BookRepository bookRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<GetBookDTO> getAll() {
        ArrayList<BookEntity> books = (ArrayList<BookEntity>) bookRepository.findAll();
        return books.stream().map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDTO getById(int id) {
        var book = bookRepository.findById(id).orElseThrow(() -> BookNotFound.create(id));
        return mapBook(book);
    }

    @Transactional
    public CreateBookResponseDTO addBook(CreateBookDTO bookDTO) {
        var bookEntity = new BookEntity();
        if (bookDTO.getAvailableCopies() < 0) throw BookInvalidNumber.create();
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setAvailableCopies(bookDTO.getAvailableCopies());
        bookEntity.setPublicationYear(bookDTO.getPublicationYear());
        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDTO(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublicationYear(), newBook.getAvailableCopies());
    }

    @Transactional
    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        boolean noPendingReturns = false;
        if (!rentalRepository.findByBookId(id).isEmpty()) {
            List<RentalEntity> rentals = rentalRepository.findByBookId(id);
            for (RentalEntity rental : rentals) {
                if (rental.getReturnDate() != null) {
                    noPendingReturns = true;
                } else {
                    throw PendingReturn.create(rental.getBook().getTitle());
                }
            }
        } else {
            if (!reviewRepository.findByBookId(id).isEmpty()) {
                bookRepository.deleteById(id);
            } else {
                reviewRepository.deleteByBookId(id);
                bookRepository.deleteById(id);
            }
        }
        if (noPendingReturns) {
            rentalRepository.deleteByBookId(id);
            reviewRepository.deleteByBookId(id);
            bookRepository.deleteById(id);
        }
    }

    public GetBookDTO editBook(EditBookDTO bookDTO) {
        BookEntity book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> BookNotFound.create(bookDTO.getId()));
        if (bookDTO.getAvailableCopies() < 0) throw BookInvalidNumber.create();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(book.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setPublisher(bookDTO.getPublisher());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        var newBook = bookRepository.save(book);
        return mapBook(newBook);
    }

    private GetBookDTO mapBook(BookEntity book) {
        return new GetBookDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getAvailableCopies() > 0);
    }
}
