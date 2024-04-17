package library.controller;

import jakarta.validation.Valid;
import library.controller.DTO.BookDTO.CreateBookResponseDTO;
import library.controller.DTO.RentalDTO.*;
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
@PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PatchMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UpdateRentalResponseDTO> updateRental(@RequestBody @Validated UpdateRentalDTO updateRentalDTO) {
        var rentalUpdated = rentalService.updateRental(updateRentalDTO);
        return new ResponseEntity<>(rentalUpdated, HttpStatus.OK);
    }
}