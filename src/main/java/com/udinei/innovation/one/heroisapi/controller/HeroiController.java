package com.udinei.innovation.one.heroisapi.controller;

import com.udinei.innovation.one.heroisapi.document.Heroi;
import com.udinei.innovation.one.heroisapi.dto.HeroiDTO;
import com.udinei.innovation.one.heroisapi.exception.HeroiNotFoundException;
import com.udinei.innovation.one.heroisapi.repository.HeroisRepository;
import com.udinei.innovation.one.heroisapi.service.HeroisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.udinei.innovation.one.heroisapi.constants.HeroiConstant.*;
@RestController
@Slf4j
public class HeroiController {

    @Autowired
    HeroisService heroisService;

    @GetMapping(HEROIS_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroi> getAllItems(){
        log.info("requesting the list off all herois");
        return heroisService.findAll();
    }

    @GetMapping(HEROIS_ENDPOINT_LOCAL + "/{id}")
    public Mono <ResponseEntity<Heroi>> findByIdHeroi(@PathVariable String id){
        log.info("requesting the heroi with id {}", id);
        // Busca heroi caso seja retornado empty retorna httpStatus - NOT_FOUND
        return heroisService.findByIdHeroi(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROIS_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroi> createHeroi(@RequestBody Heroi heroi){
        log.info("a new heroI was created");
        return  heroisService.save(heroi);
    }

    @DeleteMapping(HEROIS_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deleteIdHeroi(@PathVariable String id){
        heroisService.deleteByIdHeroi(id);
        log.info("deleting a heroi with id {}", id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }

    @PutMapping(HEROIS_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroi> updateHeroi(@RequestBody HeroiDTO heroiDTO) throws HeroiNotFoundException {
        log.info("update a heroi with id {}", heroiDTO.getId());
        return heroisService.updateHeroi(heroiDTO);
    }

}
