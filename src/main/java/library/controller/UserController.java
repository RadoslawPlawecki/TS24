package library.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import library.controller.DTO.UserDTO.GetUserFullDTO;
import library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "User")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserFullDTO> getById(@PathVariable int id) {
        GetUserFullDTO getUserDTO = userService.getById(id);
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get/me")
    public ResponseEntity<GetUserFullDTO> getByUsername(Principal principal) {
        String username = principal.getName();
        GetUserFullDTO getUserDTO = userService.getByUsername(username);
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<GetUserFullDTO>> getAll() {
        List<GetUserFullDTO> getUserDTO = userService.getAll();
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
