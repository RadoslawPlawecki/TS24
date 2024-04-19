package library.controller.DTO.AuthDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import library.common.UserRole;

public class RegisterResponseDTO {
    @Schema(name = "userId", example = "1")
    private Integer userID;
    @Schema(name = "username", example = "username")
    private String username;
    @Schema(name = "role", example = "ROLE_READER")
    private UserRole role;

    public RegisterResponseDTO(Integer userID, String username, UserRole role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
