package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findById(int id);

    List<Status> findAll();
}
