package library.controller.DTO.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import library.controller.DTO.BookDTO.GetBookDTO;
import library.controller.DTO.UserDTO.GetUserSimplifiedDTO;

import java.sql.Date;

public class GetReviewDTO {
    @Schema(name = "id", example = "1")
    private Integer id;
    @Schema(name = "book")
    private GetBookDTO book;
    @Schema(name = "userSimplified")
    private GetUserSimplifiedDTO user;
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

    public GetBookDTO getBook() {
        return book;
    }

    public void setBook(GetBookDTO book) {
        this.book = book;
    }

    public GetUserSimplifiedDTO getUser() {
        return user;
    }

    public void setUser(GetUserSimplifiedDTO user) {
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

    public GetReviewDTO() {
    }

    public GetReviewDTO(Integer id, GetBookDTO book, GetUserSimplifiedDTO user, Integer rating, String comment, Date reviewDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
