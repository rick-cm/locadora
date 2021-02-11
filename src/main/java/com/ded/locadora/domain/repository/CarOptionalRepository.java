package com.ded.locadora.domain.repository;

import com.ded.locadora.domain.entity.CarOptional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarOptionalRepository extends JpaRepository<CarOptional, Long> {

    Optional<List<CarOptional>> findByName(String name);
}
