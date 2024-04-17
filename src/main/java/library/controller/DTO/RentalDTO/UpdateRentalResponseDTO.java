package library.controller.DTO.RentalDTO;

import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.UserEntity;

import java.sql.Date;

public class UpdateRentalResponseDTO {
    private Integer id;
    private BookEntity book;
    private UserEntity user;
    private Date startDate;
    private Date endDate;
    private Date returnDate;

    public UpdateRentalResponseDTO(Integer id, BookEntity book, UserEntity user, Date startDate, Date endDate, Date returnDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
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
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
