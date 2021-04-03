package com.udinei.innovation.one.heroisapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroiDTO {

    private String id;

    private String name;

    private String universe;

    private int films;

}
