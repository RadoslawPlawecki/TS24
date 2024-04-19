package library.infrastructure.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Schema(name = "id", example = "2")
    private Integer id;
    @Basic
    @Column(name = "email", unique = true)
    @Schema(name = "email", example = "john.doe@gmail.com")
    private String email;
    @Basic
    @Column(name = "name")
    @Schema(name = "name", example = "John Doe")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
