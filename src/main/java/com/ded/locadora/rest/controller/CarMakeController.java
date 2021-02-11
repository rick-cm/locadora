package com.ded.locadora.rest.controller;

import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarMakeDto;
import com.ded.locadora.rest.dto.CarModelDto;
import com.ded.locadora.service.CarMakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car/make")
@RequiredArgsConstructor
public class CarMakeController {

    private final Validator validator;

    private final CarMakeService service;

    @GetMapping
    public List<CarMakeDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public long post(@RequestBody CarMakeDto dto){
        validate(dto);
        return service.save(dto);
    }

    @GetMapping("/{id}/models")
    public List<CarModelDto> getModels(@PathVariable long id){
        return service.getModels(id);
    }


    private void validate(CarMakeDto dto){
        Set<ConstraintViolation<CarMakeDto>> erros
                = validator.validate(dto);

        if(!erros.isEmpty()) {
            List<String> listaErros = erros.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new BadRequestException(listaErros.get(0));
        }
    }
}
