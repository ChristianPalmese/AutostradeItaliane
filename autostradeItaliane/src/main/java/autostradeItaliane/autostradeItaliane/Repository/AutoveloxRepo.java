package autostradeItaliane.autostradeItaliane.Repository;


import autostradeItaliane.autostradeItaliane.Modello.Autovelox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoveloxRepo extends JpaRepository<Autovelox,Integer> {
    public List<Autovelox> findAllByCitta(String nomeCitta);
}
