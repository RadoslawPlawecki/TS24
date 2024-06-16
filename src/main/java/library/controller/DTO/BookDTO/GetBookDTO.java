package library.controller.DTO.BookDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class GetBookDTO {
    @Schema(name = "id", example = "1")
    @NotBlank(message = "Identification number of a book is required!")
    private Integer id;
    @Schema(name = "isbn", example = "9781408855670")
    @NotBlank(message = "ISBN is required!")
    private String isbn;
    @Schema(name = "title", example = "Harry Potter and the Chamber of Secrets")
    @NotBlank(message = "Title is required!")
    private String title;
    @Schema(name = "author", example = "J.K. Rowling")
    @NotBlank(message = "Author is required!")
    private String author;
    @Schema(name = "publisher", example = "Bloomsbury")
    @NotBlank(message = "Publisher is required!")
    private String publisher;
    @Schema(name = "publicationYear", example = "2018")
    @NotBlank(message = "Publication year is required!")
    private Integer publicationYear;
    @Schema(name = "isAvailable", example = "true")
    @NotBlank(message = "Number of available copies is required!")
    private boolean isAvailable;

    public GetBookDTO(Integer id, String isbn, String title, String author, String publisher, Integer publicationYear, boolean isAvailable) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isAvailable = isAvailable;
    }

    public GetBookDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
