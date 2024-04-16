package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists {
    public static ResponseStatusException create(String username) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with the username '%s' already exists!", username));
    }
}
