package autostradeItaliane.autostradeItaliane.Controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomobileDTO {
    private int id;
    @NotBlank
    private String targa;
    @NotBlank
    private String titolare;
    @NotBlank
    private String classe;
}
