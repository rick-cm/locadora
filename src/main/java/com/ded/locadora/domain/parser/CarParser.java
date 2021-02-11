package com.ded.locadora.domain.parser;

import com.ded.locadora.domain.entity.Car;
import com.ded.locadora.domain.entity.CarModel;
import com.ded.locadora.domain.entity.CarOptional;
import com.ded.locadora.domain.enums.Transmission;
import com.ded.locadora.rest.dto.CarDto;
import com.ded.locadora.rest.dto.CarDtoPostPut;
import com.ded.locadora.rest.dto.CarOptionalDto;

import java.util.List;
import java.util.stream.Collectors;

public class CarParser {

    public static CarParser get(){ return new CarParser();}

    public Car entity(CarDto dto){

        CarModel model = new CarModel();
        model.setId(dto.getModel().getId());

        List<CarOptional> optionals = dto.getOptionals().stream().map(optional -> {
            return CarOptionalParser.get().entity(optional);
        }).collect(Collectors.toList());
        return Car.builder()
                .id(dto.getId())
                .identification(dto.getIdentification())
                .color(dto.getColor())
                .odometer(dto.getOdometer())
                .photoPath(dto.getPhotoPath())
                .transmission(Transmission.valueOf(dto.getTransmission()))
                .yearFabrication(dto.getYearFabrication())
                .yearModel(dto.getYearModel())
                .model(model)
                .optionals(optionals)
                .build();
    }

    public Car entity(CarDtoPostPut dto){

        CarModel model = new CarModel();
        model.setId(dto.getModel());

        List<CarOptional> optionals = dto.getOptionals().stream().map(aLong -> {
            CarOptional optional = new CarOptional();
            optional.setId(aLong);
            return optional;
        }).collect(Collectors.toList());
        return Car.builder()
                .identification(dto.getIdentification())
                .color(dto.getColor())
                .odometer(dto.getOdometer())
                .photoPath(dto.getPhotoPath())
                .transmission(Transmission.valueOf(dto.getTransmission()))
                .yearFabrication(dto.getYearFabrication())
                .yearModel(dto.getYearModel())
                .model(model)
                .optionals(optionals)
                .build();
    }

    public CarDto dto(Car car){
        List<CarOptionalDto> optionals = car.getOptionals().stream().map(carOptional -> {
           return CarOptionalParser.get().dto(carOptional);
        }).collect(Collectors.toList());
        return CarDto.builder()
                .id(car.getId())
                .color(car.getColor())
                .identification(car.getIdentification())
                .model(CarModelParser.get().dto(car.getModel()))
                .odometer(car.getOdometer())
                .photoPath(car.getPhotoPath())
                .transmission(car.getTransmission().name())
                .yearFabrication(car.getYearFabrication())
                .yearModel(car.getYearModel())
                .optionals(optionals)
                .build();
    }
}
