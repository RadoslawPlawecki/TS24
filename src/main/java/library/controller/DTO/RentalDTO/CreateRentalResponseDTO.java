package library.controller.DTO.RentalDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.UserEntity;

import java.sql.Date;

public class CreateRentalResponseDTO {
    @Schema(name = "id", example = "2")
    private Integer id;
    @Schema(name = "book")
    private BookEntity book;
    @Schema(name = "user")
    private UserEntity user;
    @Schema(name = "startDate", example = "2024-06-06")
    private Date startDate;
    @Schema(name = "endDate", example = "2025-05-04")
    private Date endDate;

    public CreateRentalResponseDTO(Integer id, BookEntity book, UserEntity user, Date startDate, Date endDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
}
