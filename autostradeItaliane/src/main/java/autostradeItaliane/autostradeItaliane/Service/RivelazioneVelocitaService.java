package autostradeItaliane.autostradeItaliane.Service;


import autostradeItaliane.autostradeItaliane.Controller.dto.AutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Controller.dto.RivelazioneAutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Controller.dto.RivelazioneVelocitaDTO;
import autostradeItaliane.autostradeItaliane.Exeption.AutomobileNotFound;
import autostradeItaliane.autostradeItaliane.Exeption.AutoveloxNotFound;
import autostradeItaliane.autostradeItaliane.Exeption.RivelazioneVelocitaNotFound;
import autostradeItaliane.autostradeItaliane.Modello.Automobile;
import autostradeItaliane.autostradeItaliane.Modello.Autovelox;
import autostradeItaliane.autostradeItaliane.Modello.RivelazioneVelocita;
import autostradeItaliane.autostradeItaliane.Repository.AutomobileRepo;
import autostradeItaliane.autostradeItaliane.Repository.AutoveloxRepo;
import autostradeItaliane.autostradeItaliane.Repository.RivelazioneVelocitaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class RivelazioneVelocitaService {
    @Autowired
    private RivelazioneVelocitaRepo rivelazioneVelocitaRepo;
    @Autowired
    private AutoveloxRepo autoveloxRepo;
    @Autowired
    private AutomobileRepo automobileRepo;

    /**
     * Metodo per la ricerca di un autovelox tramite il suo id e i suoi avvistamenti
     * @param id
     * @return rivelazioneAutoveloxDTO;
     */
    public RivelazioneAutoveloxDTO getAutoveloxAndAvvistamentiById(int id) {
        // Recupera l'autovelox con l'ID specificato dal repository Autovelox
        Autovelox a = autoveloxRepo.findById(id).orElseThrow(AutoveloxNotFound::new);

        // Recupera tutte le rilevazioni di velocità associate a questo autovelox
        List<RivelazioneVelocita> rivelazioneVelocita = a.getRilevazioniVelocita();
        List<RivelazioneVelocitaDTO> rivelazioneVelocitaDTOList = new ArrayList<>();
        for(RivelazioneVelocita rivelazioneVelocita1:rivelazioneVelocita){
            rivelazioneVelocitaDTOList.add(trasformazioneRivelazioneVelocitaInRivelazioneVelocitaDTO(rivelazioneVelocita1));
        }
        // Crea un nuovo oggetto RivelazioneAutoveloxDTO passando l'autovelox e le rilevazioni di velocità
        RivelazioneAutoveloxDTO rivelazioneAutoveloxDTO = new RivelazioneAutoveloxDTO(trasformazioneAutoveloxDTOInAutovelox(a),rivelazioneVelocitaDTOList );
        // Restituisce l'oggetto DTO contenente l'autovelox e le sue rilevazioni di velocità
        return rivelazioneAutoveloxDTO;
    }


    /**
     * Metodo per ottenere tutte le RivelazioniVelocita
     * @return List<RivelazioneVelocitaDTO>
     */
    public List<RivelazioneVelocitaDTO> getAllRivelazioniVelocita() {
        // Inizializza una lista vuota per la RivelazioneVelocitaDTO
        List<RivelazioneVelocitaDTO> rivelazioneVelocitaDTOList = new ArrayList<>();
        // Recupera tutte le rivelazioniVelocita dal repository
        List<RivelazioneVelocita> rivelazioneVelocitaList = rivelazioneVelocitaRepo.findAll();
        // Trasforma ciascuna rivelazione in RivelazioneVelocitaDTO e aggiungilo alla lista risultante
        for (RivelazioneVelocita r : rivelazioneVelocitaList) {
            rivelazioneVelocitaDTOList.add(trasformazioneRivelazioneVelocitaInRivelazioneVelocitaDTO(r));
        }
        // Restituisci la lista delle RivelazioneVelocitaDTO
        return rivelazioneVelocitaDTOList;
    }

    /**
     * Metodo per trovare la rivelazioneVelocita per ID
     * @param id : identifica la rivelazioneVelocita che si sta puntando
     * @return RivelazioneVelocitaDTO
     */
    public RivelazioneVelocitaDTO findRivelazioneVelocitaByID(int id) {
        // Cerca la rivelazioneVelocita nel repository tramite ID
        Optional<RivelazioneVelocita> rivelazioneVelocitaOptional=rivelazioneVelocitaRepo.findById(id);

        // Se la rivelazioneVelocita non viene trovata, lancia un'eccezione
        if (!rivelazioneVelocitaOptional.isPresent()) {
            log.info("Non è stato trovato l'utente con id {}", id);
            throw new RivelazioneVelocitaNotFound("RivelazioneVeocita con id " + id + " non trovata");
        }

        // Trasforma la rivelazioneVelocita trovato in RivelazioneVelocitaDTO e restituiscilo
        RivelazioneVelocita rivelazioneVelocita=rivelazioneVelocitaOptional.get();
        return trasformazioneRivelazioneVelocitaInRivelazioneVelocitaDTO(rivelazioneVelocita);
    }

    /**
     * Metodo per aggiungere una nuova rivelazioneVelocita
     * @param rivelazioneVelocitaDTO : contiene le informazioni per l'inserimento della rivelazioneVelocita
     * @return RivelazioneVelocitaDTO
     */
    public RivelazioneVelocitaDTO postRivelazioneVelocita(RivelazioneVelocitaDTO rivelazioneVelocitaDTO) {
        // Crea una nuova rivelazioneVelocita utilizzando i dati forniti e salvalo nel repository
        Automobile automobile=automobileRepo.findById(rivelazioneVelocitaDTO.getAutomobileID()).orElseThrow(AutomobileNotFound::new);
        Autovelox autovelox = autoveloxRepo.findById(rivelazioneVelocitaDTO.getAutoveloxID()).orElseThrow(AutoveloxNotFound::new);

        RivelazioneVelocita rivelazioneVelocita = new RivelazioneVelocita(rivelazioneVelocitaDTO.getVeloita(),rivelazioneVelocitaDTO.getDataEOra(),automobile,autovelox);
       rivelazioneVelocita = rivelazioneVelocitaRepo.save(rivelazioneVelocita);

        // Trasforma la nuova rivelazioneVelocita in rivelazioneVelocitaDTO e restituiscilo
        return trasformazioneRivelazioneVelocitaInRivelazioneVelocitaDTO(rivelazioneVelocita);
    }


    /**
     * Metodo per modificare la rivelazioneVelocita esistente
     * @param id : identifica la rivelazioneVelocita che si sta puntando
     * @param rivelazioneVelocitaDTO : contiene le informazioni per la modifica della rivelazioneVelocita
     * @return boolean viene ritornato true quando il metodo viene eseguito altrimenti ritorna false
     **/

    public boolean modificaRivelazioneVelocita(int id, RivelazioneVelocitaDTO rivelazioneVelocitaDTO) {
        // Cerca la rivelazioneVelocita nel repository tramite ID
        Optional<RivelazioneVelocita> rivelazioneVelocitaOptional = rivelazioneVelocitaRepo.findById(id);

        // Se l'automobile non viene trovato, restituisci false
        if (!rivelazioneVelocitaOptional.isPresent()) {
            log.info("Non è stato trovata la rivelazioneVelocita con questo id {}", id);
            throw new RivelazioneVelocitaNotFound("la rivelazioneVelocita con id "+ id + "non è presente");
        }
        Automobile automobile=automobileRepo.findById(rivelazioneVelocitaDTO.getAutomobileID()).orElseThrow(AutomobileNotFound::new);
        Autovelox autovelox = autoveloxRepo.findById(rivelazioneVelocitaDTO.getAutoveloxID()).orElseThrow(AutoveloxNotFound::new);

        // Modifica la rivelazioneVelocita con i nuovi dati forniti
        RivelazioneVelocita rivelazioneVelocita= rivelazioneVelocitaOptional.get();
        rivelazioneVelocita.setVelocita(rivelazioneVelocitaDTO.getVeloita());
        rivelazioneVelocita.setDataEora(rivelazioneVelocitaDTO.getDataEOra());
        rivelazioneVelocita.setAutomobile(automobile);
        rivelazioneVelocita.setAutovelox(autovelox);

        rivelazioneVelocitaRepo.save(rivelazioneVelocita);
        // Restituisci true per indicare che la modifica è avvenuta con successo
        return true;
    }




    /** Metodo per eliminare la rivelazioneVelocita per ID
     *
     * @param id l'id dell'utente da cancellare
     * @return un booleano che vale true se la cancellazione è avvenuta, false nel caso contrario
     */
    public boolean deleteRivelazioneVelocita(int id) {
        // Cerca la rivelazioneVelocita nel repository tramite ID
        Optional<RivelazioneVelocita> rivelazioneVelocitaOptional = rivelazioneVelocitaRepo.findById(id);

        // Se la rivelazioneVelocita non viene trovato, restituisci false
        if (!rivelazioneVelocitaOptional.isPresent()) {
            throw new RivelazioneVelocitaNotFound("la rivelazioneVelocita con id "+ id + "non è presente");
        }
        // Se la rivelazioneVelocita viene trovata, elimina la rivelazioneVelocita dal repository
        rivelazioneVelocitaRepo.deleteById(id);
        return true;
    }




    private AutoveloxDTO trasformazioneAutoveloxDTOInAutovelox(Autovelox autovelox){
        AutoveloxDTO autoveloxDTO= new AutoveloxDTO(autovelox.getId(),autovelox.getCodice(),autovelox.getCitta(),autovelox.getVelocita());
        return autoveloxDTO;
    }


    private RivelazioneVelocitaDTO trasformazioneRivelazioneVelocitaInRivelazioneVelocitaDTO(RivelazioneVelocita rivelazioneVelocita){
        RivelazioneVelocitaDTO rivelazioneVelocitaDTO = new RivelazioneVelocitaDTO(rivelazioneVelocita.getId(),rivelazioneVelocita.getVelocita(),rivelazioneVelocita.getDataEora(),rivelazioneVelocita.getAutomobile().getId(),rivelazioneVelocita.getAutovelox().getId());
        return rivelazioneVelocitaDTO;
    }

}
