package library.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import library.controller.DTO.BookDTO.CreateBookDTO;
import library.controller.DTO.BookDTO.CreateBookResponseDTO;
import library.controller.DTO.BookDTO.EditBookDTO;
import library.controller.DTO.BookDTO.GetBookDTO;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CreateBookResponseDTO> addBook(@RequestBody CreateBookDTO bookDTO) {
        var newBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public GetBookDTO getById(@PathVariable int id) {
        return bookService.getById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public @ResponseBody List<GetBookDTO> getAll() {
        return bookService.getAll();
    }

    @PatchMapping("/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<GetBookDTO> editBook(@RequestBody EditBookDTO bookDTO) {
        var bookEdited = bookService.editBook(bookDTO);
        return new ResponseEntity<>(bookEdited, HttpStatus.OK);
    }
}
