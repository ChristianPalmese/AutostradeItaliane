package autostradeItaliane.autostradeItaliane.Controller;

import autostradeItaliane.autostradeItaliane.Controller.dto.AutomobileDTO;
import autostradeItaliane.autostradeItaliane.Service.AutomobileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@Slf4j
public class AutomobileController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */

    @Autowired
    AutomobileService automobileService;

    @GetMapping("/automobile")
    @ResponseBody
    public List<AutomobileDTO> getAllAutomobili(){
        log.info("è stato richiesto il comando getAllAutomobili");
        return automobileService.getAllAutomobili();
    }

    @GetMapping("/automobile/{id}")
    @ResponseBody
    public AutomobileDTO findAutomobileByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando findAutomobileByID");
        return automobileService.findAutomobileByID(id);
    }

    @PostMapping("/automobile")
    @ResponseBody
    public AutomobileDTO postAutomobile(@RequestBody @Valid AutomobileDTO automobileDTO){
        log.info("è stato richiesto il comando postAutomobile");
        return automobileService.postAutomobile(automobileDTO);
    }

    @PutMapping("/automobile/{id}")
    @ResponseBody
    public boolean modificaAutomobile(@PathVariable int id, @RequestBody @Valid AutomobileDTO automobileDTO){
        log.info("è stata richiesta la modifica dell'automobile con id {} ",id);
        Boolean aBoolean=automobileService.modificaAutomobile(id,automobileDTO);
        log.info("la modifica è avvenuta : {} ", aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/automobile/{id}")
    @ResponseBody
    public boolean deletAutomobile(@PathVariable int id){
        log.info("è stata richiesta la cancellasione dell'automobile con id {} ", id);
        Boolean aBoolean=automobileService.deleteAutomobile(id);
        log.info("la cancellazione è avvenuta : {} ", aBoolean);
        return aBoolean;

    }

}
