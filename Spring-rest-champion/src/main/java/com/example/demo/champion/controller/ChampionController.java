package com.example.demo.champion.controller;

import com.example.demo.champion.dto.*;
import com.example.demo.champion.entity.Champion;
import com.example.demo.champion.entity.Race;
import com.example.demo.champion.service.ChampionService;
import com.example.demo.champion.service.RaceService;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
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

    public ChampionController(ChampionService championService, RaceService raceService, UserService userService) {
        this.championService = championService;
        this.raceService = raceService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetChampionsResponse> getChampions() {
        List<Champion> all = championService.findAll();
        GetChampionsResponse getChampionsResponse = GetChampionsResponse.entityToDtoMapper(all);
        return ResponseEntity.ok(getChampionsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetChampionResponse> getChampion(@PathVariable("id") long id) {
        return championService.find(id)
                .map(champion -> ResponseEntity.ok(GetChampionResponse.entityToDtoMapper(champion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

     /**
      * Change of convention is needed(user and character in paths are in the wrong order)
      */
    @PostMapping("users/{login}")
    public ResponseEntity<Void> createChampion(@RequestBody CreateChampionRequest request,
                                               @PathVariable("login") String login) {
        Optional<Race> race = raceService.find(request.getRace());
        if (race.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Champion champion = CreateChampionRequest.dtoToEntityMapper(request, user.get(), race.get());
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
