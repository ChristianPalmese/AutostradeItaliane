package autostradeItaliane.autostradeItaliane.Controller.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RivelazioneVelocitaDTO {
    private int id;
    @NotNull
    private int veloita;
    @NotBlank
    private LocalDateTime dataEOra;
    @NotNull
    private int automobileID;
    @NotNull
    private int autoveloxID;
}
