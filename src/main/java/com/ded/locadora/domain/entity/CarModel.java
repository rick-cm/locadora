package com.ded.locadora.domain.entity;

import com.ded.locadora.domain.enums.BodyType;
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
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private CarMake make;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bodyType=" + bodyType +
                ", make=" + make +
                ", cars=" + cars +
                '}';
    }
}
