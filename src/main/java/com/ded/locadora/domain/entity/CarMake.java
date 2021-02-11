package com.ded.locadora.domain.entity;

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
@Table(name = "car_make")
public class CarMake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "make", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CarModel> modelos;

    @Override
    public String toString() {
        return "CarMake{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelos=" + modelos +
                '}';
    }
}
