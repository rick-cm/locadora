package com.ded.locadora.rest.controller;

import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarModelDto;
import com.ded.locadora.rest.dto.CarOptionalDto;
import com.ded.locadora.service.CarOptionalService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car/optional")
@RequiredArgsConstructor
public class CarOptionalController {

    private final Validator validator;
    private final CarOptionalService service;

    @GetMapping
    public List<CarOptionalDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public long put(@RequestBody CarOptionalDto dto){
        validate(dto);
        return service.save(dto);
    }

    private void validate(CarOptionalDto dto){
        Set<ConstraintViolation<CarOptionalDto>> erros
                = validator.validate(dto);

        if(!erros.isEmpty()) {
            List<String> listaErros = erros.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new BadRequestException(listaErros.get(0));
        }
    }
}
