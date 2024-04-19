package library.controller.DTO.BookDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CreateBookDTO {
    @NotBlank(message = "ISBN is required!")
    @Schema(name = "isbn", example = "9781408855670")
    private String isbn;
    @NotBlank(message = "Title is required!")
    @Schema(name = "title", example = "Harry Potter and the Chamber of Secrets")
    private String title;
    @NotBlank(message = "Author is required!")
    @Schema(name = "author", example = "J.K. Rowling")
    private String author;
    @NotBlank(message = "Publisher is required!")
    @Schema(name = "publisher", example = "Bloomsbury")
    private String publisher;
    @NotBlank(message = "Publication year is required!")
    @Schema(name = "publicationYear", example = "2018")
    private Integer publicationYear;
    @NotBlank(message = "Number of available copies is required!")
    @Schema(name = "availableCopies", example = "60")
    private int availableCopies;

    public CreateBookDTO(String isbn, String title, String author, String publisher, Integer publicationYear, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
    }

    public CreateBookDTO() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
