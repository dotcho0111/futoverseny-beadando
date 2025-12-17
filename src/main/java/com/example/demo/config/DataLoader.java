package com.example.demo.config;

import com.example.demo.model.Race;
import com.example.demo.model.Result;
import com.example.demo.model.Runner;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.ResultRepository;
import com.example.demo.repository.RunnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    // Konstruktor alapú injektálás: a Spring automatikusan átadja a repository-kat
    public DataLoader(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Futók létrehozása (4 fő)
        Runner f1 = runnerRepository.save(new Runner(null, "Kovács Béla", 25, "Férfi"));
        Runner f2 = runnerRepository.save(new Runner(null, "Nagy Anna", 30, "Nő"));
        Runner f3 = runnerRepository.save(new Runner(null, "Szabó Gábor", 22, "Férfi"));
        Runner f4 = runnerRepository.save(new Runner(null, "Kiss Virág", 28, "Nő"));

        // 2. Versenyek létrehozása (2 verseny)
        Race v1 = raceRepository.save(new Race(null, "Szigetfutás", 5.3));
        Race v2 = raceRepository.save(new Race(null, "Városi Maraton", 42.1));

        // 3. Eredmények létrehozása (6 eredmény - összekötjük a futókat a versenyekkel)
        // paraméterek: id(null), futó, verseny, idő(perc)
        resultRepository.save(new Result(null, f1, v1, 25.5));
        resultRepository.save(new Result(null, f2, v1, 28.2));
        resultRepository.save(new Result(null, f3, v1, 22.1));
        
        resultRepository.save(new Result(null, f1, v2, 240.5));
        resultRepository.save(new Result(null, f4, v2, 210.0));
        resultRepository.save(new Result(null, f2, v2, 255.3));

        System.out.println(">> Tesztadatok sikeresen betöltve!");
    }
}