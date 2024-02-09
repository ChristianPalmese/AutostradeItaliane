package autostradeItaliane.autostradeItaliane.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RivelazioneVelocitaNotFound extends RuntimeException{
    public RivelazioneVelocitaNotFound() {
    }

    public RivelazioneVelocitaNotFound(String message) {
        super(message);
    }

    public RivelazioneVelocitaNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RivelazioneVelocitaNotFound(Throwable cause) {
        super(cause);
    }
}
