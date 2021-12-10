package com.example.springrestchampion.champion.controller;

import com.example.springrestchampion.champion.dto.*;
import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.champion.service.RaceService;
import com.example.springrestchampion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/champions")
public class ChampionController {

    private ChampionService championService;

    private RaceService raceService;

    private UserService userService;

    @Autowired
    public ChampionController(ChampionService championService, RaceService raceService, UserService userService) {
        this.championService = championService;
        this.raceService = raceService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetChampionsResponse> getChampions() {
        List<Champion> all = championService.findAll();
        GetChampionsResponse getChampionsResponse = GetChampionsResponse.entityToDtoMapper(all);
        return ResponseEntity.ok(getChampionsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetChampionResponse> getChampion(@PathVariable("id") Long id) {
        return championService.find(id)
                .map(champion -> ResponseEntity.ok(GetChampionResponse.entityToDtoMapper(champion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> createChampion(@RequestBody CreateChampionRequest request) {
        Optional<Race> race = raceService.find(request.getRace());
        if (race.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Champion champion = CreateChampionRequest.dtoToEntityMapper(request, race.get(), null);
        championService.add(champion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateChampion(@RequestBody UpdateChampionRequest request,
                                               @PathVariable("id") Long id) {
        Optional<Champion> champion = championService.find(id);
        if (champion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UpdateChampionRequest.dtoToEntityUpdater(request, champion.get());
        championService.update(champion.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChampion(@PathVariable("id") Long id){
        Optional<Champion> champion = championService.find(id);
        if(champion.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        championService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
