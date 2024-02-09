package autostradeItaliane.autostradeItaliane.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AutoveloxNotFound extends RuntimeException{

    public AutoveloxNotFound() {
    }

    public AutoveloxNotFound(String message) {
        super(message);
    }

    public AutoveloxNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoveloxNotFound(Throwable cause) {
        super(cause);
    }
}
