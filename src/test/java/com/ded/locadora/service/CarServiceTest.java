package com.ded.locadora.service;

import com.ded.locadora.domain.entity.Car;
import com.ded.locadora.domain.entity.CarMake;
import com.ded.locadora.domain.entity.CarModel;
import com.ded.locadora.domain.enums.BodyType;
import com.ded.locadora.domain.enums.Transmission;
import com.ded.locadora.domain.parser.CarParser;
import com.ded.locadora.domain.repository.CarModelRepository;
import com.ded.locadora.domain.repository.CarOptionalRepository;
import com.ded.locadora.domain.repository.CarRepository;
import com.ded.locadora.exception.BadRequestException;
import com.ded.locadora.rest.dto.CarDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarServiceTest {


    @Mock CarRepository repository;
    @Mock CarModelRepository modelRepository;
    @Mock CarOptionalRepository optionalRepository;

    @InjectMocks CarService service;

    @Mock CarParser carParser;

    @Test()
    void getShouldReturnBadRequestException() {

        Car car = Mockito.mock(Car.class);
        CarDto carDto = Mockito.mock(CarDto.class);
        carDto.setId(1);
        Mockito.when(carParser.dto(car)).thenReturn(carDto);

        Exception exception = assertThrows(BadRequestException.class, () -> Mockito.when(service.get(1)).thenReturn(carDto));

        String expectedMessage = "Carro de id = '"+1+"' não encontrado.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    void get_shouldReturnCarDto(){
        Car car = Car.builder()
                .color("azul")
                .id(1)
                .model(CarModel.builder()
                        .bodyType(BodyType.CAMINHONETE)
                        .make(CarMake.builder().name("teste").build())
                        .build())
                .identification("1321312")
                .odometer("23132132")
                .optionals(new ArrayList<>())
                .photoPath("asdas")
                .transmission(Transmission.MANUAL)
                .yearFabrication("123213")
                .yearModel("1231232")
                .build();
        CarDto carDto = CarParser.get().dto(car);

        Mockito.when(repository.findById(Long.valueOf("1"))).thenReturn(Optional.of(car));

        assertEquals(service.get(1).getId(), carDto.getId());
    }

    @Test
    void getAll() {
    }

    @Test
    void save() {
    }

    @Test
    void put() {
    }

    @Test
    void delete() {
    }
}