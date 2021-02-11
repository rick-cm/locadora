package com.ded.locadora.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarMakeDto {

    private long id;

    @NotEmpty(message = "Campo nome deve estar preenchido")
    private String name;
}
