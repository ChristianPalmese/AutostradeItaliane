package autostradeItaliane.autostradeItaliane.Service;

import autostradeItaliane.autostradeItaliane.Controller.dto.AutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Controller.dto.StatisticheAutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Exeption.AutoveloxNotFound;
import autostradeItaliane.autostradeItaliane.Modello.Autovelox;
import autostradeItaliane.autostradeItaliane.Repository.AutoveloxRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AutoveloxService {
    @Autowired
    AutoveloxRepo autoveloxRepo;


    /**
     * Metodo per ottenere tutti gli autovelox
     * @return List<AutoveloxDTO>
     */
    public List<AutoveloxDTO> getAllAutovelox() {
        // Inizializza una lista vuota per le AutoveloxDTO
        List<AutoveloxDTO> autoveloxDTOListDTOList = new ArrayList<>();
        // Recupera tutti gli autovelox dal repository
        List<Autovelox> autoveloxList = autoveloxRepo.findAll();
        // Trasforma ciascun autovelox in autoveloxDTO e aggiungilo alla lista risultante
        for (Autovelox a : autoveloxList) {
            autoveloxDTOListDTOList.add(trasformazioneAutoveloxDTOInAutovelox(a));
        }
        // Restituisci la lista delle automobiliDTO
        return  autoveloxDTOListDTOList;
    }

    /**
     * Metodo per trovare un'autovelox per ID
     * @param id : identifica l'autovelox che si sta puntando
     * @return AutoveloxDTO
     */
    public AutoveloxDTO findAutoveloxByID(int id) {
        // Cerca un'autovelox nel repository tramite ID
        Optional<Autovelox> autoveloxOptional = autoveloxRepo.findById(id);

        // Se l'autovelox non viene trovata, lancia un'eccezione
        if (!autoveloxOptional.isPresent()) {
            log.info("Non è stato trovato l'utente con id {}", id);
            throw new AutoveloxNotFound("Autovelox con id " + id + " non trovata");
        }

        // Trasforma l'autovelox trovato in AutoveloxDTO e restituiscilo
        Autovelox autovelox=autoveloxOptional.get();
        return trasformazioneAutoveloxDTOInAutovelox(autovelox);
    }

    /**
     * Metodo per aggiungere un nuovo autovelox
     * @param autoveloxDTO : contiene le informazioni per l'inserimento dell'autovelox
     * @return AutoveloxDTO
     */
    public AutoveloxDTO postAutovelox(AutoveloxDTO autoveloxDTO) {
        // Crea un nuovo autovelox utilizzando i dati forniti e salvalo nel repository
        Autovelox autovelox = new Autovelox(autoveloxDTO.getCodice(),autoveloxDTO.getCitta(),autoveloxDTO.getVelocita());
        autovelox = autoveloxRepo.save(autovelox);

        // Trasforma il nuovo autovelox in Autovelox e restituiscilo
        return trasformazioneAutoveloxDTOInAutovelox(autovelox);
    }

    /**
     * Metodo per modificare un'autovelox esistente
     * @param id : identifica l'autovelox che si sta puntando
     * @param autoveloxDTO : contiene le informazioni per la modifica dell'autovelox
     * @return boolean viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     */
    public boolean modificaAutovelox(int id, AutoveloxDTO autoveloxDTO) {
        // Cerca l'autovelox nel repository tramite ID
        Optional<Autovelox> autoveloxOptional = autoveloxRepo.findById(id);

        // Se l'autovelox non viene trovato, restituisci false
        if (!autoveloxOptional.isPresent()) {
            log.info("Non è stato trovato l'autovelox con questo id {}", id);
            throw new AutoveloxNotFound("l'atleta con id "+ id + "non è presente");
        }
        // Modifica l'autovelox con i nuovi dati forniti
        Autovelox autovelox = autoveloxOptional.get();
        autovelox.setCitta(autoveloxDTO.getCitta());
        autovelox.setCodice(autoveloxDTO.getCodice());
        autovelox.setVelocita(autoveloxDTO.getVelocita());

        // Restituisci true per indicare che la modifica è avvenuta con successo
        return true;
    }

    /** Metodo per eliminare un'autovelox per ID
     *
     * @param id l'id dell'utente da cancellare
     * @return un booleano che vale true se la cancellazione è avvenuta, false nel caso contrario
     */
    public boolean deleteAutovelox(int id) {
        // Cerca l'autovelox nel repository tramite ID
        Optional<Autovelox> autoveloxOptional = autoveloxRepo.findById(id);

        // Se l'autovelox non viene trovato, restituisci false
        if (!autoveloxOptional.isPresent()) {
            throw new AutoveloxNotFound("l'autovelox con id "+ id + "non è presente");
        }
        // Se l'autovelox viene trovata, elimina l'autovelox dal repository
        autoveloxRepo.deleteById(id);
        return true;
    }


    public List<AutoveloxDTO>  findAllAutoveloxByCitta (String nomeCitta){
        List<Autovelox> autoveloxList=autoveloxRepo.findAllByCitta(nomeCitta);
        List<AutoveloxDTO> autoveloxDTOList = new ArrayList<>();
        for(Autovelox a:autoveloxList){
            autoveloxDTOList.add(trasformazioneAutoveloxDTOInAutovelox(a));
        }
        return autoveloxDTOList;
    }


    public StatisticheAutoveloxDTO findStatisticheByCitta(String nomeCitta) {
        List<Autovelox> autoveloxList = autoveloxRepo.findAllByCitta(nomeCitta);

        int numeroElementi = autoveloxList.size();
        int velocitaMassima = 0;
        int velocitaMinima = 100000;


        // Calcola la somma delle velocità e trova la velocità massima
        for (Autovelox autovelox : autoveloxList) {
            int velocitaAutovelox = autovelox.getVelocita();
            if (velocitaAutovelox > velocitaMassima) {
                velocitaMassima = velocitaAutovelox;
            }
            if(velocitaAutovelox < velocitaMinima){
                velocitaMinima = velocitaAutovelox;
            }
        }

        // Crea l'oggetto StatisticheAutoveloxDTO
        StatisticheAutoveloxDTO statisticheAutoveloxDTO = new StatisticheAutoveloxDTO(nomeCitta, numeroElementi, velocitaMassima, velocitaMinima);

        return statisticheAutoveloxDTO;
    }




    private AutoveloxDTO trasformazioneAutoveloxDTOInAutovelox(Autovelox autovelox){
        AutoveloxDTO autoveloxDTO= new AutoveloxDTO(autovelox.getId(),autovelox.getCodice(),autovelox.getCitta(),autovelox.getVelocita());
        return autoveloxDTO;
    }
}
