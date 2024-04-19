package library.controller.DTO.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetUserSimplifiedDTO {
    @Schema(name = "id", example = "2")
    private Integer id;
    @Schema(name = "name", example = "John Doe")
    private String name;
    @Schema(name = "email", example = "john.doe@gmail.com")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GetUserSimplifiedDTO(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public GetUserSimplifiedDTO() {
    }
}
