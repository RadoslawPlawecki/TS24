package library.controller;

import library.controller.DTO.BookDTO.CreateBookResponseDTO;
import library.controller.DTO.RentalDTO.CreateRentalDTO;
import library.controller.DTO.RentalDTO.CreateRentalResponseDTO;
import library.controller.DTO.RentalDTO.GetRentalDTO;
import library.infrastructure.entity.RentalEntity;
import library.service.RentalService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CreateRentalResponseDTO> addRental(@RequestBody @Validated CreateRentalDTO rental) {
        var newRental = rentalService.addRental(rental);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalDTO> getOne(@PathVariable int id) {
        GetRentalDTO getRentalDTO = rentalService.getOne(id);
        return new ResponseEntity<>(getRentalDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<GetRentalDTO>> getAll() {
        List<GetRentalDTO> getRentalDTO = rentalService.getAll();
        return new ResponseEntity<>(getRentalDTO, HttpStatus.OK);
    }
}