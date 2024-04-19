package library.controller.DTO.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.UserEntity;

import java.sql.Date;

public class EditReviewResponseDTO {
    @Schema(name = "id", example = "2")
    private Integer id;
    @Schema(name = "book")
    private BookEntity book;
    @Schema(name = "user")
    private UserEntity user;
    @Schema(name = "rating", example = "10")
    private Integer rating;
    @Schema(name = "comment", example = "Amazing!")
    private String comment;
    @Schema(name = "reviewDate", example = "2024-04-19")
    private Date reviewDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public EditReviewResponseDTO(Integer id, BookEntity book, UserEntity user, Integer rating, String comment, Date reviewDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public EditReviewResponseDTO() {
    }
}
