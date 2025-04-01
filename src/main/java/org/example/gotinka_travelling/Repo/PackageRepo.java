package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

    public interface PackageRepo extends JpaRepository<Packages, Integer> {
        List<Packages> findByIsDeletedFalse();

        @Query("SELECT p.packageName FROM Packages p")
        List<String> findAllPackageNames();

        @Query("SELECT p.packageId FROM Packages p WHERE p.packageName =:name")
        Integer findPackageIdByNames(String name);

        @Query("SELECT COUNT(p) FROM Packages p WHERE p.active = true AND p.isDeleted = false")
        Long countActivePackages();

}
