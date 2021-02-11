package com.ded.locadora.domain.parser;

import com.ded.locadora.domain.entity.CarOptional;
import com.ded.locadora.rest.dto.CarOptionalDto;

public class CarOptionalParser {

    public static CarOptionalParser get() {return new CarOptionalParser();}

    public CarOptional entity(CarOptionalDto dto){
        return CarOptional.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public CarOptionalDto dto(CarOptional entity){
        return CarOptionalDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
