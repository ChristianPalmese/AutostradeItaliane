package autostradeItaliane.autostradeItaliane.Modello;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 6)
    private String targa;
    @Column (length = 50)
    private String titolare;
    @Column (length = 1)
    private String classe;
    @OneToMany(mappedBy = "automobile", cascade = CascadeType.ALL)
    private List<RivelazioneVelocita> rilevazioniVelocita;

    public Automobile(String targa, String titolare, String classe) {
        this.targa = targa;
        this.titolare = titolare;
        this.classe = classe;
    }
}
