package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Integer> {
    List<Guide> findByIsDeletedFalse();
}