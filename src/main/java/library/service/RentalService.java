package library.service;

import library.controller.DTO.BookDTO.GetBookDTO;
import library.controller.DTO.RentalDTO.CreateRentalDTO;
import library.controller.DTO.RentalDTO.CreateRentalResponseDTO;
import library.controller.DTO.RentalDTO.GetRentalDTO;
import library.controller.DTO.UserDTO.GetUserDTO;
import library.exception.BookNotFound;
import library.exception.RentalNotFound;
import library.exception.UserNotFound;
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
import java.util.Optional;
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

    public List<GetRentalDTO> getAll() {
        List<RentalEntity> rentals = (List<RentalEntity>) rentalRepository.findAll();
        return rentals.stream().map(this::mapRental).collect(Collectors.toList());
    }

    public GetRentalDTO getOne(int id) {
        RentalEntity rentalEntity = rentalRepository.findById(id).orElseThrow(() -> RentalNotFound.create(id));
        return mapRental(rentalEntity);
    }

    @Transactional
    public CreateRentalResponseDTO addRental(CreateRentalDTO rentalDTO) {
        UserEntity user = userRepository.findById(rentalDTO.getUserId()).orElseThrow(() -> UserNotFound.create(rentalDTO.getUserId()));
        BookEntity book = bookRepository.findById(rentalDTO.getBookId()).orElseThrow(() -> BookNotFound.create(rentalDTO.getBookId()));
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setBook(book);
        rentalEntity.setUser(user);
        rentalEntity.setStartDate(new Date(System.currentTimeMillis()));
        rentalEntity.setEndDate(rentalDTO.getEndDate());
        var newRental = rentalRepository.save(rentalEntity);
        return new CreateRentalResponseDTO(newRental.getId(), newRental.getBook(), newRental.getUser(), newRental.getStartDate(), newRental.getEndDate());
    }

    public void deleteRental(int id) {
        if (!rentalRepository.existsById(id)) {
            throw RentalNotFound.create(id);
        }
        rentalRepository.deleteById(id);
    }

    private GetRentalDTO mapRental(RentalEntity rental) {
        GetBookDTO book = new GetBookDTO(rental.getBook().getId(), rental.getBook().getIsbn(), rental.getBook().getTitle(), rental.getBook().getAuthor(), rental.getBook().getPublisher(), rental.getBook().getPublicationYear(), rental.getBook().getAvailableCopies() > 0);
        GetUserDTO user = new GetUserDTO(rental.getUser().getId(), rental.getUser().getName(), rental.getUser().getEmail(), rental.getUser().getFullName());
        return new GetRentalDTO(rental.getId(), book, user, rental.getStartDate(), rental.getEndDate());
    }
}
