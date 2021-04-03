package com.udinei.innovation.one.heroisapi.mapper;

import com.udinei.innovation.one.heroisapi.document.Heroi;
import com.udinei.innovation.one.heroisapi.dto.HeroiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * Essa classe utiliza o MapStruct - gerador de código que implementa mapeamentos entre tipos de bean Java
 * nessa caso entre model Heroi e o DataTransference HeroiDTO
 * */
@Mapper
public interface HeroiMapper {

    HeroiMapper INSTANCE = Mappers.getMapper(HeroiMapper.class);

    Heroi toModel(HeroiDTO heroiDTO);
    HeroiDTO toDTO(Heroi heroi);

}
