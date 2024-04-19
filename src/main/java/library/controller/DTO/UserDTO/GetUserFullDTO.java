package library.controller.DTO.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetUserFullDTO {
    @Schema(name = "id", example = "2")
    private Integer id;
    @Schema(name = "name", example = "John Doe")
    private String name;
    @Schema(name = "username", example = "username")
    private String username;
    @Schema(name = "email", example = "john.doe@gmail.com")
    private String email;
    @Schema(name = "role", example = "ROLE_READER")
    private String role;

    public GetUserFullDTO() {
    }
    public GetUserFullDTO(Integer id, String name, String username, String email, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
