package library.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import library.LoginForm;
import library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${jwt.toke.key}")
    private String key;

    @Autowired
    public LoginService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String login(LoginForm loginForm) {
        // User user = userRepository.getUserByLogin(LoginForm.getLogin());
        if (passwordEncoder.matches(loginForm.getPassword(), /*userRepository.getPassword*/ "")) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 5 * 60 * 1000)) // expire after 5 minutes
                    .claim("id", "userID")
                    .claim("userRole", "ROLE_")
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }
}
