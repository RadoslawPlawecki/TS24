package library.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ApiResponse(responseCode = "201", description = "User registered")
    public ResponseEntity<RegisterResponseDTO> register(@Validated @RequestBody RegisterDTO registerDTO) {
        RegisterResponseDTO registerResponseDTO = authService.register(registerDTO);
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login succeeded"),
            @ApiResponse(responseCode = "401", description = "Login failed", content = @Content)
    })
    public ResponseEntity<LoginResponseDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.CREATED);
    }
}
