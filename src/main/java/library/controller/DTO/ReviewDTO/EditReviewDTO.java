package library.controller.DTO.ReviewDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;

public class EditReviewDTO {
    @Schema(name = "id", example = "1")
    private Integer id;
    @Schema(name = "rating", example = "10")
    private Integer rating;
    @Schema(name = "comment", example = "Amazing!")
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EditReviewDTO(Integer id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    public EditReviewDTO() {
    }
}
