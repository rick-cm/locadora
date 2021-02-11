package com.ded.locadora.service;

import com.ded.locadora.domain.entity.CarOptional;
import com.ded.locadora.domain.parser.CarOptionalParser;
import com.ded.locadora.domain.repository.CarOptionalRepository;
import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarOptionalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarOptionalService {

    private final CarOptionalRepository repository;

    public List<CarOptionalDto> getAll(){
        return repository.findAll().stream().map(CarOptionalParser.get()::dto).collect(Collectors.toList());
    }

    public long save(CarOptionalDto dto){
        CarOptional entity = CarOptionalParser.get().entity(dto);
        entity.setId(0);
        validate(entity);
        return repository.save(entity).getId();
    }

    private void validate(CarOptional optional){
        if(optional.getId() == 0){
            repository.findByName(optional.getName()).ifPresent(carOptionals ->
                    carOptionals.stream().forEach(carOptional -> {
                        if(carOptional.getName() == optional.getName()) throw new BadRequestException("Este opcional já está cadastrado!");
                    }));
        }
    }
}
