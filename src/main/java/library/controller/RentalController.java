package library.controller;

import library.infrastructure.entity.RentalEntity;
import library.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<RentalEntity> addRental(@RequestBody RentalEntity rental) {
        var newRental = rentalService.addRental(rental);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public RentalEntity getOne(@PathVariable int id) {
        return rentalService.getOne(id);
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<RentalEntity> getAll() {
        return rentalService.getAll();
    }
}
