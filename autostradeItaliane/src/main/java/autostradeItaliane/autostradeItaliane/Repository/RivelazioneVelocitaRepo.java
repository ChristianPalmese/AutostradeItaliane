package autostradeItaliane.autostradeItaliane.Repository;

import autostradeItaliane.autostradeItaliane.Modello.Autovelox;
import autostradeItaliane.autostradeItaliane.Modello.RivelazioneVelocita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RivelazioneVelocitaRepo extends JpaRepository<RivelazioneVelocita,Integer> {

    public List<RivelazioneVelocita> findAllByAutovelox_Id(int id);
}
