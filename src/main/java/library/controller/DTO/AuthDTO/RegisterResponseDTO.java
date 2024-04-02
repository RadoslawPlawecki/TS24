package library.controller.DTO.AuthDTO;

import library.common.UserRole;

public class RegisterResponseDTO {
    private Integer userID;
    private String username;
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
