package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Ez jelzi, hogy JSON adatokat fogunk visszaadni
public class RunnerRestController {

    private final RunnerRepository runnerRepo;
    private final RaceRepository raceRepo;
    private final ResultRepository resultRepo;

    // A Spring ide "injektálja" (beadja) nekünk a repository-kat
    public RunnerRestController(RunnerRepository runnerRepo, RaceRepository raceRepo, ResultRepository resultRepo) {
        this.runnerRepo = runnerRepo;
        this.raceRepo = raceRepo;
        this.resultRepo = resultRepo;
    }

    // 1. Összes futó listázása
    @GetMapping("/getRunners")
    public List<Runner> getRunners() {
        return runnerRepo.findAll();
    }

    // 2. Futó felvétele
    @PostMapping("/addRunner")
    public Runner addRunner(@Valid @RequestBody Runner runner) {
        return runnerRepo.save(runner);
    }

    // 3. Adott verseny futói és időeredményei (sorbarendezve)
    @GetMapping("/getRaceRunners/{id}")
    public List<Result> getRaceRunners(@PathVariable Long id) {
        return resultRepo.findByRaceIdOrderByTimeAsc(id);
    }

    // 4. Verseny frissítése
    @PostMapping("/updateRace")
    public Race updateRace(@RequestBody Race raceDetails) {
        // Megkeressük az eredetit az ID alapján
        Race race = raceRepo.findById(raceDetails.getId())
                .orElseThrow(() -> new RuntimeException("Race not found"));
        // Átírjuk az adatait
        race.setName(raceDetails.getName());
        race.setDistance(raceDetails.getDistance());
        // Elmentjük a változásokat
        return raceRepo.save(race);
    }

    // 5. Új eredmény rögzítése
    @PostMapping("/addResult")
    public Result addResult(@RequestBody Result result) {
        // A JSON-ben érkező Result-ot elmentjük
        return resultRepo.save(result);
    }

    // 6. Átlagidő számítása
    @GetMapping("/getAverageTime/{id}")
    public Double getAverageTime(@PathVariable Long id) {
        List<Result> results = resultRepo.findByRaceIdOrderByTimeAsc(id);
        if (results.isEmpty()) return 0.0;

        return results.stream()
                .mapToDouble(Result::getTime)
                .average()
                .orElse(0.0);
    }
}