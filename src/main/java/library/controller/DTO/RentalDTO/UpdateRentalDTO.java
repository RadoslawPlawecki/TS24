package library.controller.DTO.RentalDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public class UpdateRentalDTO {
    @NotBlank(message = "Identification number of a rental is required!")
    @Schema(name = "id", example = "1")
    private Integer id;
    @NotBlank(message = "Return date is required!")
    @Schema(name = "returnDate", example = "2024-08-07")
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
