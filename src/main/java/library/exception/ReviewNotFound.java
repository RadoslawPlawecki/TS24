package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFound {
    public static ResponseStatusException create(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Review with id '%s' was not found!", id));
    }
}
