package library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RatingOverScale {
    public static ResponseStatusException create() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "The maximum rating is equalled to 10!");
    }
}
