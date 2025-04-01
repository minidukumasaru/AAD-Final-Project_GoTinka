package org.example.gotinka_travelling.Repo;

import org.example.gotinka_travelling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail(String userName);

    boolean existsByEmail(String userName);

    int deleteByEmail(String userName);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'USER'")
    Long countUsersByRoleUser();



}