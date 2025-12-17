package com.example.demo.repository;

import com.example.demo.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    // Alap CRUD műveletek itt is elérhetőek lesznek.
}