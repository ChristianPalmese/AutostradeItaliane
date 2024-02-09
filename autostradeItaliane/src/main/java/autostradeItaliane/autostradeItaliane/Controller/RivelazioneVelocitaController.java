package autostradeItaliane.autostradeItaliane.Controller;


import autostradeItaliane.autostradeItaliane.Controller.dto.RivelazioneAutoveloxDTO;
import autostradeItaliane.autostradeItaliane.Controller.dto.RivelazioneVelocitaDTO;
import autostradeItaliane.autostradeItaliane.Service.RivelazioneVelocitaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Slf4j
public class RivelazioneVelocitaController {
    /*5 comandi base via rest:
    1 getAll
    2 getById
    3 create creazione nuova entita
    4 modifica di un entita gia esistente
    5 cancellazione di una entita
     */
    @Autowired
    RivelazioneVelocitaService rivelazioneVelocitaService;

    @GetMapping("/rivelazioneVelocita")
    @ResponseBody
    public List<RivelazioneVelocitaDTO> rivelazioneVelocitaGetAll(){
        log.info("è stato richiesto il comando rivelazioneVelocitaGetAll");
        return rivelazioneVelocitaService.getAllRivelazioniVelocita();
    }

    @GetMapping("/rivelazioneVelocita/{id}")
    @ResponseBody
    public RivelazioneVelocitaDTO findRivelazioneVelocitaByID(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando findRivelazioneVelocitaByID");
        return rivelazioneVelocitaService.findRivelazioneVelocitaByID(id);
    }

    @PostMapping("/rivelazioneVelocita")
    @ResponseBody
    public RivelazioneVelocitaDTO postRivelazioneVelocita(@RequestBody @Valid RivelazioneVelocitaDTO rivelazioneVelocitaDTO){
        log.info("è stato richiesto il comando postRivelazioneVelocita");
        return rivelazioneVelocitaService.postRivelazioneVelocita(rivelazioneVelocitaDTO);
    }


    @PutMapping("/rivelazioneVelocita/{id}")
    @ResponseBody
    public boolean modificaRivelazioneVelocita(@PathVariable int id, @RequestBody @Valid RivelazioneVelocitaDTO rivelazioneVelocitaDTO){
        log.info("è stata richiesta la modifica della rivelazioneVelocita con id {} ",id);
        Boolean aBoolean=rivelazioneVelocitaService.modificaRivelazioneVelocita(id,rivelazioneVelocitaDTO);
        log.info("la modifica è avvenuta : {} ", aBoolean);
        return aBoolean;
    }



    @DeleteMapping("/rivelazioneVelocita/{id}")
    @ResponseBody
    public boolean deletRivelazioneVelocita(@PathVariable int id){
        log.info("è stata richiesta la cancellasione della rivelazioneVelocita con id {} ", id);
        Boolean aBoolean=rivelazioneVelocitaService.deleteRivelazioneVelocita(id);
        log.info("la cancellazione è avvenuta : {} ", aBoolean);
        return aBoolean;

    }

    @GetMapping("/rivelazioneAutovelox/{id}")
    @ResponseBody
    public RivelazioneAutoveloxDTO getAutoveloxAndAvvistamentiById(@PathVariable(required = true) int id){
        log.info("è stato richiesto il comando findRivelazioneVelocitaByID");
        return rivelazioneVelocitaService.getAutoveloxAndAvvistamentiById(id);
    }






}
