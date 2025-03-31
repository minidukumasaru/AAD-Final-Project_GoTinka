package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByIsDeletedFalse();
}
