package library.service;

import library.common.PasswordValidator;
import library.controller.DTO.AuthDTO.LoginDTO;
import library.controller.DTO.AuthDTO.LoginResponseDTO;
import library.controller.DTO.AuthDTO.RegisterDTO;
import library.controller.DTO.AuthDTO.RegisterResponseDTO;
import library.exception.IncorrectPassword;
import library.exception.UserAlreadyExists;
import library.exception.WeakPassword;
import library.infrastructure.entity.AuthEntity;
import library.infrastructure.entity.UserEntity;
import library.infrastructure.repository.AuthRepository;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public RegisterResponseDTO register(RegisterDTO registerDTO) {
        Optional<AuthEntity> auth = authRepository.findByUsername(registerDTO.getUsername());
        if (auth.isPresent()) {
            throw UserAlreadyExists.create(registerDTO.getUsername());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setName(registerDTO.getName());
        userRepository.save(userEntity);
        AuthEntity authEntity = new AuthEntity();
        if (!PasswordValidator.isValidPassword(registerDTO.getPassword())) {
            throw WeakPassword.create();
        }
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
            throw IncorrectPassword.create();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDTO(token);
    }
}
