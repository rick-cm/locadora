package com.ded.locadora.domain.parser;

import com.ded.locadora.domain.entity.CarMake;
import com.ded.locadora.domain.entity.CarModel;
import com.ded.locadora.domain.enums.BodyType;
import com.ded.locadora.domain.parser.mapper.CarMakeMapper;
import com.ded.locadora.rest.dto.CarModelDto;
import com.ded.locadora.rest.dto.CarModelDtoPostPut;

public class CarModelParser {

    public static CarModelParser get(){return new CarModelParser();}

    public CarModel entity(CarModelDtoPostPut dto){
        CarMake make = new CarMake();
        make.setId(dto.getMake());
        return CarModel.builder()
                .bodyType(BodyType.valueOf(dto.getBodyType()))
                .name(dto.getName())
                .make(make)
                .build();
    }

    public CarModelDto dto(CarModel entity){
        return CarModelDto.builder()
                .id(entity.getId())
                .bodyType(entity.getBodyType().name())
                .make(CarMakeMapper.INSTANCE.dto(entity.getMake()))
                .name(entity.getName())
                .build();
    }
}
