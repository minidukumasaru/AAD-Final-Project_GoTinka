package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Integer> {
    List<Guide> findByIsDeletedFalse();

    @Query("SELECT COUNT(g) FROM Guide g WHERE g.available = true AND g.isDeleted = false")
    Long countAvailableGuides();
}