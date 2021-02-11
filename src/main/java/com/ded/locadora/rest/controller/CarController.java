package com.ded.locadora.rest.controller;

import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarDto;
import com.ded.locadora.rest.dto.CarDtoPostPut;
import com.ded.locadora.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final Validator validator;
    private final CarService service;

    @GetMapping("/{id}")
    public CarDto get(@PathVariable long id){
        return service.get(id);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll(){
        return new ResponseEntity<List<CarDto>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public HttpStatus post(@Valid @RequestBody CarDtoPostPut dto){
        validate(dto);
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public HttpStatus put(@Valid @RequestBody CarDtoPostPut dto, @PathVariable long id){
        validate(dto);
        return service.put(dto, id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id){
        return service.delete(id);
    }

    private void validate(CarDtoPostPut dto){
        Set<ConstraintViolation<CarDtoPostPut>> erros
                = validator.validate(dto);

        if(!erros.isEmpty()) {
            List<String> listaErros = erros.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new BadRequestException(listaErros.get(0));
        }
    }
}
