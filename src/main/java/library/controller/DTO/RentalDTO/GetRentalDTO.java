package library.controller.DTO.RentalDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import library.controller.DTO.BookDTO.GetBookDTO;
import library.controller.DTO.UserDTO.GetUserSimplifiedDTO;

import java.sql.Date;

public class GetRentalDTO {
    @Schema(name = "id", example = "1")
    private Integer id;
    @Schema(name = "book")
    private GetBookDTO book;
    @Schema(name = "userSimplified")
    private GetUserSimplifiedDTO user;
    @Schema(name = "startDate", example = "2024-06-06")
    private Date startDate;
    @Schema(name = "endDate", example = "2025-05-04")
    private Date endDate;
    @Schema(name = "wasReturned", example = "true")
    private boolean wasReturned;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isWasReturned() {
        return wasReturned;
    }

    public void setWasReturned(boolean wasReturned) {
        this.wasReturned = wasReturned;
    }

    public GetRentalDTO() {
    }

    public GetRentalDTO(Integer id, GetBookDTO book, GetUserSimplifiedDTO user, Date startDate, Date endDate, boolean wasReturned) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wasReturned = wasReturned;
    }
}
