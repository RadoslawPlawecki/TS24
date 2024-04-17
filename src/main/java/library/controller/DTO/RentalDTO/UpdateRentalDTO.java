package library.controller.DTO.RentalDTO;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public class UpdateRentalDTO {
    @NotBlank(message = "Id of a rental is required!")
    private Integer id;
    @NotBlank(message = "Return date is required!")
    private Date returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public UpdateRentalDTO(Integer id, Date returnDate) {
        this.id = id;
        this.returnDate = returnDate;
    }

    public UpdateRentalDTO() {
    }
}
