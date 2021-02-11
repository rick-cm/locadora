package com.ded.locadora.domain.entity;

import com.ded.locadora.domain.enums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String identification;
    private String color;
    private String odometer;
    private String photoPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission")
    private Transmission transmission;

    private String yearFabrication;
    private String yearModel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToMany
    @JoinTable(name="car_has_optionals", joinColumns=
            {@JoinColumn(name="car_id")}, inverseJoinColumns=
            {@JoinColumn(name="optional_id")})
    private List<CarOptional> optionals;
}
