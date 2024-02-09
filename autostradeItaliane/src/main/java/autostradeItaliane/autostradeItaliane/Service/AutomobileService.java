package autostradeItaliane.autostradeItaliane.Service;

import autostradeItaliane.autostradeItaliane.Controller.dto.AutomobileDTO;
import autostradeItaliane.autostradeItaliane.Exeption.AutomobileNotFound;
import autostradeItaliane.autostradeItaliane.Modello.Automobile;
import autostradeItaliane.autostradeItaliane.Repository.AutomobileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AutomobileService {
    @Autowired
    AutomobileRepo automobileRepo;


    /**
     * Metodo per ottenere tutte le auto
     * @return List<AutomobileDTO>
     */
    public List<AutomobileDTO> getAllAutomobili() {
        // Inizializza una lista vuota per le AutomobiliDTO
        List<AutomobileDTO> automobileDTOListDTOList = new ArrayList<>();
        // Recupera tutte le automobili dal repository
        List<Automobile> automobileList = automobileRepo.findAll();
        // Trasforma ciascuna automobile in automobileDTO e aggiungilo alla lista risultante
        for (Automobile a : automobileList) {
            automobileDTOListDTOList.add(trasformazioneAutomobileDTOInAutomobile(a));
        }
        // Restituisci la lista delle automobiliDTO
        return automobileDTOListDTOList;
    }


    /**
     * Metodo per trovare un'automobile per ID
     * @param id : identifica l'automobile che si sta puntando
     * @return AutomobileDTO
     */
    public AutomobileDTO findAutomobileByID(int id) {
        // Cerca un'automobile nel repository tramite ID
        Optional<Automobile> automobileOptional = automobileRepo.findById(id);

        // Se l'automobile non viene trovata, lancia un'eccezione
        if (!automobileOptional.isPresent()) {
            log.info("Non è stato trovato l'utente con id {}", id);
            throw new AutomobileNotFound("Auto con id " + id + " non trovata");
        }

        // Trasforma l'automobile trovato in AutomobileDTO e restituiscilo
        Automobile automobile=automobileOptional.get();
        return trasformazioneAutomobileDTOInAutomobile(automobile);
    }


    /**
     * Metodo per aggiungere una nuova automobile
     * @param automobileDTO : contiene le informazioni per l'inserimento dell'automobile
     * @return AutomobileDTO
     */
    public AutomobileDTO postAutomobile(AutomobileDTO automobileDTO) {
        // Crea una nuova automobile utilizzando i dati forniti e salvalo nel repository
        Automobile automobile = new Automobile(automobileDTO.getClasse(),automobileDTO.getTarga(),automobileDTO.getTitolare());
        automobile = automobileRepo.save(automobile);

        // Trasforma la nuova automobile in AutomobileDTO e restituiscilo
        return trasformazioneAutomobileDTOInAutomobile(automobile);
    }


    /**
     * Metodo per modificare un'automobile esistente
     * @param id : identifica l'automobile che si sta puntando
     * @param automobileDTO : contiene le informazioni per la modifica dell'automobile
     * @return boolean viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     */
    public boolean modificaAutomobile(int id, AutomobileDTO automobileDTO) {
        // Cerca l'automobile nel repository tramite ID
        Optional<Automobile> automobileOptional = automobileRepo.findById(id);

        // Se l'automobile non viene trovato, restituisci false
        if (!automobileOptional.isPresent()) {
            log.info("Non è stato trovato l'automobile con questo id {}", id);
            throw new AutomobileNotFound("l'atleta con id "+ id + "non è presente");
        }
        // Modifica l'automobile con i nuovi dati forniti
        Automobile automobile = automobileOptional.get();
        automobile.setClasse(automobile.getClasse());
        automobile.setTarga(automobile.getTarga());
        automobile.setTitolare(automobile.getTitolare());

        // Restituisci true per indicare che la modifica è avvenuta con successo
        return true;
    }


    /** Metodo per eliminare un'automobile per ID
     *
     * @param id l'id dell'utente da cancellare
     * @return un booleano che vale true se la cancellazione è avvenuta, false nel caso contrario
     */
    public boolean deleteAutomobile(int id) {
        // Cerca l'automobile nel repository tramite ID
        Optional<Automobile> automobileOptional = automobileRepo.findById(id);

        // Se l'automobile non viene trovato, restituisci false
        if (!automobileOptional.isPresent()) {
            throw new AutomobileNotFound("l'automobile con id "+ id + "non è presente");
        }
        // Se l'automobile viene trovata, elimina l'automobile dal repository
        automobileRepo.deleteById(id);
        return true;
    }

















    private AutomobileDTO trasformazioneAutomobileDTOInAutomobile(Automobile automobile){
        AutomobileDTO automobileDTO= new AutomobileDTO(automobile.getId(), automobile.getClasse(), automobile.getTarga(), automobile.getTitolare());
        return automobileDTO;
    }
}
