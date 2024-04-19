package library.controller.DTO.AuthDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import library.common.UserRole;

public class RegisterDTO {
    @NotBlank(message = "Name is required!")
    @Schema(name = "name", example = "John Doe")
    private String name;
    @NotBlank(message = "Username is required!")
    @Schema(name = "username", example = "username")
    private String username;
    @NotBlank(message = "Password is required!")
    @Schema(name = "password", example = "Password@Me00")
    private String password;
    @NotNull
    @Schema(name = "role", example = "ROLE_READER")
    private UserRole role;
    @NotBlank(message = "Email is required!")
    @Schema(name = "email", example = "john.doe@gmail.com")
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterDTO() {
    }

    public RegisterDTO(String name, String username, String password, UserRole role, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }
}
