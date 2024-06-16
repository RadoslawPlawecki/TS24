package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PendingReturn {
    public static ResponseStatusException create(String title) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("The book '%1$s' hasn't been returned yet!", title));
    }
}
