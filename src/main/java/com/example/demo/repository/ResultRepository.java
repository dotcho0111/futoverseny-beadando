package com.example.demo.repository;

import com.example.demo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    /**
     * Ez a metódus megkeresi az összes eredményt egy adott versenyhez (raceId alapján),
     * majd sorba rendezi őket az időeredmény (time) szerint növekvő (Ascending) sorrendben.
     */
    List<Result> findByRaceIdOrderByTimeAsc(Long raceId);
}