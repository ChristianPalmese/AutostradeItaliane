package autostradeItaliane.autostradeItaliane.Controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticheAutoveloxDTO {

    private String citta;

    private int numeroAutovelox;

    private int velocitaMassima;

    private int velocitaMinimo;
}
