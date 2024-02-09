package autostradeItaliane.autostradeItaliane.Modello;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RivelazioneVelocita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int velocita;
    private LocalDateTime dataEora;
    @ManyToOne
    private Automobile automobile;
    @ManyToOne
    private Autovelox autovelox;

    public RivelazioneVelocita(int velocita, LocalDateTime dataEora, Automobile automobile, Autovelox autovelox) {
        this.velocita = velocita;
        this.dataEora = dataEora;
        this.automobile = automobile;
        this.autovelox = autovelox;
    }
}
