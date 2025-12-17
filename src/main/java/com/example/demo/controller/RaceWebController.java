package com.example.demo.controller;

import com.example.demo.model.Race;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.ResultRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RaceWebController {

    private final RaceRepository raceRepo;
    private final ResultRepository resultRepo;

    public RaceWebController(RaceRepository raceRepo, ResultRepository resultRepo) {
        this.raceRepo = raceRepo;
        this.resultRepo = resultRepo;
    }

    // Versenyek listázása oldal
    @GetMapping("/races")
    public String listRaces(Model model) {
        model.addAttribute("races", raceRepo.findAll());
        return "races"; // Ez a templates/races.html fájlra mutat
    }

    // Új verseny létrehozása (FORM mentése)
    @PostMapping("/races/save")
    public String saveRace(@ModelAttribute Race race) {
        raceRepo.save(race);
        return "redirect:/races"; // Mentés után visszaküld a listához
    }

    // Verseny részletei oldal
    @GetMapping("/races/{id}")
    public String raceDetails(@PathVariable Long id, Model model) {
        Race race = raceRepo.findById(id).get();
        model.addAttribute("race", race);
        model.addAttribute("results", resultRepo.findByRaceIdOrderByTimeAsc(id));
        return "race-details"; // templates/race-details.html
    }
}