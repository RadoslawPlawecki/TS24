package library.controller.DTO.RentalDTO;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateRentalDTO {
    @NotNull
    private Integer bookId;
    @NotNull
    private Integer userId;
    @NotNull
    private Date endDate;

    public CreateRentalDTO() {
    }

    public CreateRentalDTO(Integer bookId, Integer userId, Date endDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.endDate = endDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}