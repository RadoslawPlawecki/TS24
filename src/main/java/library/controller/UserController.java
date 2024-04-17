package library.controller;

import library.controller.DTO.UserDTO.GetUserFullDTO;
import library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<GetUserFullDTO> getOne(@PathVariable int id) {
        GetUserFullDTO getUserDTO = userService.getOne(id);
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<GetUserFullDTO>> getAll() {
        List<GetUserFullDTO> getUserDTO = userService.getAll();
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
