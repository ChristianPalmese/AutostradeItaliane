package autostradeItaliane.autostradeItaliane.Repository;

import autostradeItaliane.autostradeItaliane.Modello.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileRepo extends JpaRepository<Automobile,Integer> {
}
