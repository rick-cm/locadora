package com.ded.locadora.service;

import com.ded.locadora.domain.entity.CarModel;
import com.ded.locadora.domain.parser.CarModelParser;
import com.ded.locadora.domain.repository.CarModelRepository;
import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarModelDto;
import com.ded.locadora.rest.dto.CarModelDtoPostPut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository repository;

    public List<CarModelDto> getAll(){
        return repository.findAll().stream().map(CarModelParser.get()::dto).collect(Collectors.toList());
    }

    public long save(CarModelDtoPostPut dto){
        CarModel model = CarModelParser.get().entity(dto);
        model.setId(0);
        validate(model);
        return repository.save(model).getId();
    }

    private void validate(CarModel model){
        if(model.getId() == 0){
            repository.findByName(model.getName()).ifPresent(carModels ->
                    carModels.stream().forEach(carModel -> {
                        System.out.println(carModel.getName());
                        if(carModel.getMake().getId() == model.getMake().getId()) throw new BadRequestException("Este modelo já está cadastrado!");
                    }));
        }
    }

}
