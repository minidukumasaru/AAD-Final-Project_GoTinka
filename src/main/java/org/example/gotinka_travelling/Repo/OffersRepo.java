package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffersRepo extends JpaRepository<Offers, Integer> {
    List<Offers> findByIsDeletedFalse();
}
