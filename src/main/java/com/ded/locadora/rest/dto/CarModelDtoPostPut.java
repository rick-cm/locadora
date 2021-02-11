package com.ded.locadora.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDtoPostPut {

    @NotEmpty(message = "Campo nome deve estar preenchido")
    private String name;

    @NotEmpty(message = "Campo tipo deve estar preenchido")
    private String bodyType;

    @NotNull(message = "Campo marca deve estar preenchido")
    private long make;
}
