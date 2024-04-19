package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundForId {
    public static ResponseStatusException create(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id '%s' was not found!", id));
    }
}
