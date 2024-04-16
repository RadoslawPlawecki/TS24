package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectPassword {
    public static ResponseStatusException create() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "The password is incorrect!");
    }
}
