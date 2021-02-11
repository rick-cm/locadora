package com.ded.locadora.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    private long id;

    private String name;

    private String bodyType;

    private CarMakeDto make;
}
