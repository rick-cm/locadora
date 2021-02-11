package com.ded.locadora.domain.parser.mapper;
import com.ded.locadora.domain.entity.CarMake;
import com.ded.locadora.rest.dto.CarMakeDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public abstract class CarMakeMapper {

    public static final CarMakeMapper INSTANCE = Mappers.getMapper( CarMakeMapper.class );

    @Mapping(source = "name", target = "name")
    public abstract CarMake entity(CarMakeDto dto);

    @Mapping(source = "name", target = "name")
    public abstract CarMakeDto dto(CarMake entity);
}