package com.ded.locadora.service;

import com.ded.locadora.domain.entity.Car;
import com.ded.locadora.domain.parser.CarParser;
import com.ded.locadora.domain.repository.CarModelRepository;
import com.ded.locadora.domain.repository.CarOptionalRepository;
import com.ded.locadora.domain.repository.CarRepository;
import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarDto;
import com.ded.locadora.rest.dto.CarDtoPostPut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class CarService {

    private final CarRepository repository;
    private final CarModelRepository modelRepository;
    private final CarOptionalRepository optionalRepository;

    public CarDto get(long id){
        return CarParser.get().dto(repository.findById(id).orElseThrow(() -> new BadRequestException("Carro de id = '"+id+"' não encontrado.")));
    }

    public List<CarDto> getAll(){
        return repository.findAll().stream().map(car -> CarParser.get().dto(car)).collect(Collectors.toList());
    }

    @Transactional
    public HttpStatus save(CarDtoPostPut dto){
        Car car = CarParser.get().entity(dto);
        validate(car);
        repository.save(car);
        return HttpStatus.CREATED;
    }

    @Transactional
    public HttpStatus put(CarDtoPostPut dto, long id){
        repository.findById(id).orElseThrow(() -> new BadRequestException("Carro de id = '"+id+"' não encontrado!"));
        Car car = CarParser.get().entity(dto);
        car.setId(id);
        validate(car);
        repository.save(car);
        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(long id){
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw new BadRequestException("Carro de id = '"+id+"' não encontrado!");
        }
        return HttpStatus.OK;
    }

    private void validate(Car car){
        if(car.getId() == 0) {
            repository.findByIdentification(car.getIdentification())
                        .ifPresent(car1 -> {throw new BadRequestException("A identificação informada já está em uso.");}
                );
        }else{
            repository.findByIdentification(car.getIdentification())
                    .ifPresent(car1 -> {
                        if (car.getId() != car1.getId()) throw new BadRequestException("A identificação informada já está em uso.");
                    });
        }

        modelRepository.findById(car.getModel().getId()).orElseThrow(() -> new BadRequestException("O modelo informado não existe."));

        car.getOptionals().stream().forEach(carOptional -> {
           optionalRepository.findById(carOptional.getId()).orElseThrow(() -> new BadRequestException("O optional = '"+carOptional.getId()+"' não existe."));
        });
    }
}
