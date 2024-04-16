package library.controller.DTO.RentalDTO;

import library.controller.DTO.BookDTO.GetBookDTO;
import library.controller.DTO.UserDTO.GetUserDTO;
import library.infrastructure.entity.BookEntity;
import library.infrastructure.entity.UserEntity;

import java.sql.Date;

public class GetRentalDTO {
    private Integer id;
    private GetBookDTO book;
    private GetUserDTO user;
    private Date startDate;
    private Date endDate;

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

    public GetUserDTO getUser() {
        return user;
    }

    public void setUser(GetUserDTO user) {
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

    public GetRentalDTO() {
    }

    public GetRentalDTO(Integer id, GetBookDTO book, GetUserDTO user, Date startDate, Date endDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
