package autostradeItaliane.autostradeItaliane.Controller.dto;

import autostradeItaliane.autostradeItaliane.Modello.Autovelox;
import autostradeItaliane.autostradeItaliane.Modello.RivelazioneVelocita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RivelazioneAutoveloxDTO {
    private AutoveloxDTO autoveloxDTO;
    private List<RivelazioneVelocitaDTO> listRivelazioneDTO;
}
