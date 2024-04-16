package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WeakPassword {
    public static ResponseStatusException create() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "The password is too weak! Must have a minimum of 8 characters, including an uppercase letter, a lowercase letter, a number and a special character.");
    }
}
