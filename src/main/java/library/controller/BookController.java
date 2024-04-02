package library.controller;

import library.controller.DTO.BookDTO.CreateBookDTO;
import library.controller.DTO.BookDTO.CreateBookResponseDTO;
import library.controller.DTO.BookDTO.GetBookDTO;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CreateBookResponseDTO> addBook(@RequestBody CreateBookDTO book) {
        var newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public GetBookDTO getOne(@PathVariable int id) {
        return bookService.getOne(id);
    }

    @GetMapping("/get")
    public @ResponseBody List<GetBookDTO> getAll() {
        return bookService.getAll();
    }
}
