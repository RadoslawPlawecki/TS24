package library.infrastructure.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books", schema = "library")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Schema(name = "id", example = "2")
    private Integer id;
    @Basic
    @Column(name = "isbn", unique = true)
    @Schema(name = "isbn", example = "9781408855670")
    private String isbn;
    @Basic
    @Column(name = "title")
    @Schema(name = "title", example = "Harry Potter and the Chamber of Secrets")
    private String title;
    @Basic
    @Column(name = "author")
    @Schema(name = "author", example = "J.K. Rowling")
    private String author;
    @Basic
    @Column(name = "publisher")
    @Schema(name = "publisher", example = "Bloomsbury")
    private String publisher;
    @Basic
    @Column(name = "publication_year")
    @Schema(name = "publicationYear", example = "2018")
    private Integer publicationYear;
    @Basic
    @Column(name = "available_copies")
    @Schema(name = "availableCopies", example = "60")
    private Integer availableCopies;


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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}