package library.controller.DTO.ReviewDTO;

import java.sql.Date;

public class EditReviewDTO {
    private Integer id;
    private Integer rating;
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
