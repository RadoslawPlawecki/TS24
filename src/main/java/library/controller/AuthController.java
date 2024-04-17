package library.controller;

import library.controller.DTO.AuthDTO.LoginDTO;
import library.controller.DTO.AuthDTO.LoginResponseDTO;
import library.controller.DTO.AuthDTO.RegisterDTO;
import library.controller.DTO.AuthDTO.RegisterResponseDTO;
import library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("hasRole('ADMIN')")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Validated @RequestBody RegisterDTO registerDTO) {
        RegisterResponseDTO registerResponseDTO = authService.register(registerDTO);
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.CREATED);
    }
}
