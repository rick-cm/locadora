package com.ded.locadora.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CarDtoPostPut {

    @NotEmpty(message = "Campo identificacao deve estar preenchido")
    private String identification;
    @NotEmpty(message = "Campo color deve estar preenchido")
    private String color;
    @NotEmpty(message = "Campo odometro deve estar preenchido")
    private String odometer;
    @NotEmpty(message = "Campo photoPath deve estar preenchido")
    private String photoPath;
//    private MultipartFile photoPath;
    @NotEmpty(message = "Campo transmissao deve estar preenchido")
    private String transmission;
    @NotEmpty(message = "Campo ano de fabricacao deve estar preenchido")
    private String yearFabrication;
    @NotEmpty(message = "Campo ano do modelo deve estar preenchido")
    private String yearModel;
    @NotNull(message = "Campo modelo deve estar preenchido")
    private long model;
    @NotNull(message = "Campo opcionais deve estar preenchido")
    private List<Long> optionals;
}
