package com.udinei.innovation.one.heroisapi.service;

import com.udinei.innovation.one.heroisapi.mapper.HeroiMapper;
import com.udinei.innovation.one.heroisapi.document.Heroi;
import com.udinei.innovation.one.heroisapi.dto.HeroiDTO;
import com.udinei.innovation.one.heroisapi.exception.HeroiNotFoundException;
import com.udinei.innovation.one.heroisapi.repository.HeroisRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Classe de serviço Reativa usando Flux e Mono que são implementações da interface Reactive Streams Publisher.
 * Flux - utilizado no processamento de stream assíncrono.
 *        em um fluxo que pode emitir elementos 0..n.e em seguida, é concluído (com êxito ou com erro).
 * Mono -  também utilizado no processamento de stream assíncrono
 *         em um fluxo de 0..1
 */

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired)) // gera metodo construtor com todos atributos
public class HeroisService {


    private HeroisRepository heroisRepository;

    private final HeroiMapper heroiMapper = HeroiMapper.INSTANCE;

    // retorna todos os herois cadastrados no BD dynamoDB
    public Flux<Heroi> findAll(){
        return Flux.fromIterable(this.heroisRepository.findAll());
    }

    // retorna heroi que possui o id passado como parametro
    public Mono <Heroi> findByIdHeroi(String id){
        return Mono.justOrEmpty(this.heroisRepository.findById(id));
    }

    // Salva o heroi passado como parametro no banco e o retorna
    public Mono<Heroi> save(Heroi heroi){
        return Mono.justOrEmpty(this.heroisRepository.save(heroi));
    }

    // deleta o heroi que possui o id passado como parametro e retorna true
    public Mono<Boolean> deleteByIdHeroi(String id){
        heroisRepository.deleteById(id);
        return Mono.just(true);
    }


    // alterar o heroi, caso nao exista lanca uma exception
    public Mono<Heroi> updateHeroi( HeroiDTO heroiDTO) throws HeroiNotFoundException {
        Heroi savedHeroi = null;
        Optional<Heroi> heroi = this.heroisRepository.findById(heroiDTO.getId());

        if (heroi.isEmpty())
            // lanca a execption informando o nome heroi nao encotrado
            throw new HeroiNotFoundException(heroiDTO.getName());
            savedHeroi = this.heroisRepository.save(heroiMapper.toModel(heroiDTO));

           return Mono.justOrEmpty(savedHeroi);

    }
}