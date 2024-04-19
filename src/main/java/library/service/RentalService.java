package library.service;

import library.controller.DTO.BookDTO.GetBookDTO;
import library.controller.DTO.RentalDTO.*;
import library.controller.DTO.UserDTO.GetUserSimplifiedDTO;
import library.exception.BookNotAvailable;
import library.exception.BookNotFound;
import library.exception.RentalNotFound;
import library.exception.UserNotFoundForId;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.RentalEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.BookRepository;
import library.infrastructure.repository.RentalRepository;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<GetRentalDTO> getAll(Integer userId) {
        List<RentalEntity> rentals;
        if (userId == null) {
            rentals = (List<RentalEntity>) rentalRepository.findAll();
        } else {
            rentals = rentalRepository.findByUserId(userId);
        }
        return rentals.stream().map(this::mapRental).collect(Collectors.toList());
    }

    public GetRentalDTO getById(int id) {
        RentalEntity rentalEntity = rentalRepository.findById(id).orElseThrow(() -> RentalNotFound.create(id));
        return mapRental(rentalEntity);
    }

    @Transactional
    public CreateRentalResponseDTO addRental(CreateRentalDTO rentalDTO) {
        UserEntity user = userRepository.findById(rentalDTO.getUserId()).orElseThrow(() -> UserNotFoundForId.create(rentalDTO.getUserId()));
        BookEntity book = bookRepository.findById(rentalDTO.getBookId()).orElseThrow(() -> BookNotFound.create(rentalDTO.getBookId()));
        if (book.getAvailableCopies() == 0) throw BookNotAvailable.create(book.getTitle(), book.getIsbn());
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setBook(book);
        rentalEntity.setUser(user);
        rentalEntity.setStartDate(new Date(System.currentTimeMillis()));
        rentalEntity.setEndDate(rentalDTO.getEndDate());
        var newRental = rentalRepository.save(rentalEntity);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return new CreateRentalResponseDTO(newRental.getId(), newRental.getBook(), newRental.getUser(), newRental.getStartDate(), newRental.getEndDate());
    }

    public void deleteRental(int id) {
        if (!rentalRepository.existsById(id)) {
            throw RentalNotFound.create(id);
        }
        rentalRepository.deleteById(id);
    }

    public UpdateRentalResponseDTO updateRental(UpdateRentalDTO rentalDTO) {
        RentalEntity rental = rentalRepository.findById(rentalDTO.getId()).orElseThrow(() -> RentalNotFound.create(rentalDTO.getId()));
        rental.setReturnDate(rentalDTO.getReturnDate());
        rentalRepository.save(rental);
        BookEntity book = bookRepository.findById(rental.getBook().getId()).orElseThrow(() -> BookNotFound.create(rental.getBook().getId()));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return new UpdateRentalResponseDTO(rental.getId(), rental.getBook(), rental.getUser(), rental.getStartDate(), rental.getEndDate(), rental.getReturnDate());
    }

    private GetRentalDTO mapRental(RentalEntity rental) {
        GetBookDTO book = new GetBookDTO(rental.getBook().getId(), rental.getBook().getIsbn(), rental.getBook().getTitle(), rental.getBook().getAuthor(), rental.getBook().getPublisher(), rental.getBook().getPublicationYear(), rental.getBook().getAvailableCopies() > 0);
        GetUserSimplifiedDTO user = new GetUserSimplifiedDTO(rental.getUser().getId(), rental.getUser().getName(), rental.getUser().getEmail());
        return new GetRentalDTO(rental.getId(), book, user, rental.getStartDate(), rental.getEndDate(), rental.getReturnDate() != null);
    }
}
