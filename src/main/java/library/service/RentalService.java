package library.service;

import library.infrastructure.entity.RentalEntity;
import library.infrastructure.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Iterable<RentalEntity> getAll() {
        return rentalRepository.findAll();
    }

    public RentalEntity getOne(int id) {
        return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found!"));
    }

    public RentalEntity addRental(RentalEntity rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new RuntimeException("The rental doesn't exist in the database!");
        }
        rentalRepository.deleteById(id);
    }
}
