package com.ded.locadora.domain.repository;

import com.ded.locadora.domain.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long>{

    Optional<List<CarModel>> findByName(String name);
}
