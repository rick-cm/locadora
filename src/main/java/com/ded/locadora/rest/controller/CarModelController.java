package com.ded.locadora.rest.controller;

import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarModelDto;
import com.ded.locadora.rest.dto.CarModelDtoPostPut;
import com.ded.locadora.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car/model")
@RequiredArgsConstructor
public class CarModelController {

    private final Validator validator;
    private final CarModelService service;

    @GetMapping
    public List<CarModelDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public long put(@RequestBody CarModelDtoPostPut dto){
        validate(dto);
        return service.save(dto);
    }

    private void validate(CarModelDtoPostPut dto){
        Set<ConstraintViolation<CarModelDtoPostPut>> erros
                = validator.validate(dto);

        if(!erros.isEmpty()) {
            List<String> listaErros = erros.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new BadRequestException(listaErros.get(0));
        }
    }
}
