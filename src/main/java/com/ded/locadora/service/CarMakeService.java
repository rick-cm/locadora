package com.ded.locadora.service;

import com.ded.locadora.domain.entity.CarMake;
import com.ded.locadora.domain.parser.CarModelParser;
import com.ded.locadora.domain.parser.mapper.CarMakeMapper;
import com.ded.locadora.domain.repository.CarMakeRepository;
import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarMakeDto;
import com.ded.locadora.rest.dto.CarModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarMakeService {

    private final CarMakeRepository repository;

    public List<CarMakeDto> getAll(){
        return repository.findAll().stream().map(carMake -> CarMakeMapper.INSTANCE.dto(carMake)).collect(Collectors.toList());
    }

    public long save(CarMakeDto dto){
        dto.setId(0);
        CarMake make = CarMakeMapper.INSTANCE.entity(dto);
        validate(make);
        return repository.save(make).getId();
    }

    public List<CarModelDto> getModels(long id){
        CarMake make = repository.findById(id).orElseThrow(() -> new BadRequestException("Marca não encontrada."));
        return make.getModelos().stream().map(CarModelParser.get()::dto).collect(Collectors.toList());
    }

    private void validate(CarMake make){
        if(make.getId() == 0){
            Optional<CarMake> make1 = repository.findByName(make.getName());
            if(!make1.isEmpty()){ throw new BadRequestException("Esta marca já está cadastrada.");}
        }
    }
}
