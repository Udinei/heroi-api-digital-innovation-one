package com.udinei.innovation.one.heroisapi.repository;


import com.udinei.innovation.one.heroisapi.document.Heroi;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// Interface para repositorio da entidade Herois
@EnableScan
public interface HeroisRepository extends CrudRepository<Heroi,String> {
}
