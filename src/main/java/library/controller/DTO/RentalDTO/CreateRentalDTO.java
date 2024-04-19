package library.controller.DTO.RentalDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateRentalDTO {
    @NotNull
    @Schema(name = "bookId", example = "2")
    private Integer bookId;
    @NotNull
    @Schema(name = "userId", example = "5")
    private Integer userId;
    @NotNull
    @Schema(name = "endDate", example = "2025-05-04")
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