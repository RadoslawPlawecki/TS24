package library.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import library.controller.DTO.RentalDTO.*;
import library.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Rental")
@CrossOrigin
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CreateRentalResponseDTO> addRental(@RequestBody @Validated CreateRentalDTO rentalDTO) {
        var newRental = rentalService.addRental(rentalDTO);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalDTO> getById(@PathVariable int id) {
        GetRentalDTO getRentalDTO = rentalService.getById(id);
        return new ResponseEntity<>(getRentalDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<GetRentalDTO>> getAll(@RequestParam(required = false) Integer userId) {
        List<GetRentalDTO> getRentalDTO = rentalService.getAll(userId);
        return new ResponseEntity<>(getRentalDTO, HttpStatus.OK);
    }

    @PatchMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UpdateRentalResponseDTO> updateRental(@RequestBody UpdateRentalDTO rentalDTO) {
        var rentalUpdated = rentalService.updateRental(rentalDTO);
        return new ResponseEntity<>(rentalUpdated, HttpStatus.OK);
    }
}