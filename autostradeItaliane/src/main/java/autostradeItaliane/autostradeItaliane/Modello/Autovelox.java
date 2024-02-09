package autostradeItaliane.autostradeItaliane.Modello;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Autovelox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String codice;
    @Column (length = 50)
    private String citta;
    private int velocita;
    @OneToMany(mappedBy = "autovelox", cascade = CascadeType.ALL)
    private List<RivelazioneVelocita> rilevazioniVelocita;

    public Autovelox(String codice, String citta, int velocita) {
        this.codice = codice;
        this.citta = citta;
        this.velocita = velocita;
    }
}
