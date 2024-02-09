package autostradeItaliane.autostradeItaliane.Controller;


import autostradeItaliane.autostradeItaliane.Controller.dto.AutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Controller.dto.StatisticheAutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Service.AutoveloxService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j

public class AutoveloxController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */

    @Autowired
    AutoveloxService autoveloxService;

    @GetMapping("/autovelox")
    @ResponseBody
    public List<AutoveloxDTO> getAllAutovelox(){
        log.info("è stato richiesto il comando getAllAutovelox");
        return autoveloxService.getAllAutovelox();
    }

    @GetMapping("/autovelox/{id}")
    @ResponseBody
    public AutoveloxDTO findAutoveloxByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando findAutoveloxByID");
        return autoveloxService.findAutoveloxByID(id);
    }

    @PostMapping("/autovelox")
    @ResponseBody
    public AutoveloxDTO postAutovelox(@RequestBody @Valid AutoveloxDTO autoveloxDTO){
        log.info("è stato richiesto il comando postAutovelox");
        return autoveloxService.postAutovelox(autoveloxDTO);
    }

    @PutMapping("/autovelox/{id}")
    @ResponseBody
    public boolean modificaAutovelox(@PathVariable int id, @RequestBody @Valid AutoveloxDTO autoveloxDTO){
        log.info("è stata richiesta la modifica dell'autovelox con id {} ",id);
        Boolean aBoolean=autoveloxService.modificaAutovelox(id,autoveloxDTO);
        log.info("la modifica è avvenuta : {} ", aBoolean);
        return aBoolean;
    }

    @DeleteMapping("/autovelox/{id}")
    @ResponseBody
    public boolean deletAutovelox(@PathVariable int id){
        log.info("è stata richiesta la cancellasione dell'autovelox con id {} ", id);
        Boolean aBoolean=autoveloxService.deleteAutovelox(id);
        log.info("la cancellazione è avvenuta : {} ", aBoolean);
        return aBoolean;
    }

    @GetMapping("/autovelox/findByCitta")
    @ResponseBody
    public List<AutoveloxDTO> findAllAutoveloxByCitta(@RequestParam(value = "nomeCitta")String nomeCitta){
        log.info("è stato richiesto il comando  findAllAutoveloxByCitta");
        return autoveloxService.findAllAutoveloxByCitta(nomeCitta);
    }

    @GetMapping("/autovelox/statisticheByCitta")
    @ResponseBody
    public StatisticheAutoveloxDTO findStatisticheByCitta(@RequestParam(value = "nomeCitta")String nomeCitta){
        log.info("è stato richiesto il comando  findStatisticheByCitta");
        return autoveloxService.findStatisticheByCitta(nomeCitta);
    }


}
