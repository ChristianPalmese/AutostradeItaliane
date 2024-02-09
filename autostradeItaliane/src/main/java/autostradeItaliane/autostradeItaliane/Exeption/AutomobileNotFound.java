package autostradeItaliane.autostradeItaliane.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AutomobileNotFound extends RuntimeException{
    public AutomobileNotFound() {
    }

    public AutomobileNotFound(String message) {
        super(message);
    }

    public AutomobileNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomobileNotFound(Throwable cause) {
        super(cause);
    }
}
