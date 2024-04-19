package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundForUsername {
    public static ResponseStatusException create(String username) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username '%s' was not found!", username));
    }
}
