package library.controller.DTO.UserDTO;

public class GetUserDTO {
    private Integer id;
    private String name;
    private String email;
    private String fullName;

    public GetUserDTO() {
    }

    public GetUserDTO(Integer id, String name, String email, String fullName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
