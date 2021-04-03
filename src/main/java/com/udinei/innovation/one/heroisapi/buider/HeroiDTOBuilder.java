package com.udinei.innovation.one.heroisapi.buider;

import com.udinei.innovation.one.heroisapi.dto.HeroiDTO;
import lombok.Builder;

@Builder
public class HeroiDTOBuilder {

    @Builder.Default
    private String id = "2";

    @Builder.Default
    private String name = "Capitao America";

    @Builder.Default
    private String universe = "Marvel";

    @Builder.Default
    private int films = 2;

    // retorna um heroi preenchido
    public HeroiDTO toHeroiDTO(){
         return new HeroiDTO(id, name, universe,films);
    }
}
