package library.service;

import library.controller.DTO.AuthDTO.LoginDTO;
import library.controller.DTO.AuthDTO.LoginResponseDTO;
import library.controller.DTO.AuthDTO.RegisterDTO;
import library.controller.DTO.AuthDTO.RegisterResponseDTO;
import library.infrastructure.entity.AuthEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.AuthRepository;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDTO register(RegisterDTO registerDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userRepository.save(userEntity);
        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        authEntity.setUsername(registerDTO.getUsername());
        authEntity.setRole(registerDTO.getRole());
        authEntity.setUser(userEntity);
        authRepository.save(authEntity);
        return new RegisterResponseDTO(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        AuthEntity authEntity = authRepository.findByUsername(loginDTO.getUsername()).orElseThrow(RuntimeException::new);
        if (!passwordEncoder.matches(loginDTO.getPassword(), authEntity.getPassword())) {
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDTO(token);
    }
}
