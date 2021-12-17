package com.example.springrestchampion.champion.controller;

import com.example.springrestchampion.champion.dto.CreateChampionRequest;
import com.example.springrestchampion.champion.dto.GetChampionResponse;
import com.example.springrestchampion.champion.dto.GetChampionsResponse;
import com.example.springrestchampion.champion.dto.UpdateChampionRequest;
import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.champion.service.RaceService;
import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users/{login}/champions")
public class UserChampionController {

    private UserService userService;

    private ChampionService championService;

    private RaceService raceService;

    @Autowired
    public UserChampionController(UserService userService, ChampionService championService, RaceService raceService) {
        this.userService = userService;
        this.championService = championService;
        this.raceService = raceService;
    }

    @GetMapping("")
    public ResponseEntity<GetChampionsResponse> getUserChampions(@PathVariable("login") String login) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Champion> all = championService.findAll(user.get());
        GetChampionsResponse getChampionsResponse = GetChampionsResponse.entityToDtoMapper(all);
        return ResponseEntity.ok(getChampionsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetChampionResponse> getUserChampion(@PathVariable("login") String login,
                                                               @PathVariable("id") Long id) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return championService.find(id, user.get())
                .map(champion -> ResponseEntity.ok(GetChampionResponse.entityToDtoMapper(champion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<CreateChampionRequest> createChampion(@PathVariable("login") String login,
                                                                @RequestBody CreateChampionRequest request) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Race> race = raceService.find(request.getRace());
        if (race.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CreateChampionRequest.dtoToEntityMapper(request, race.get(), user.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChampion(@PathVariable("login") String login,
                                               @PathVariable("id") Long id) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Champion> champion = championService.find(id, user.get());
        if (champion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        championService.delete(champion.get().getId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateChampion(@PathVariable("login") String login,
                                               @RequestBody UpdateChampionRequest request,
                                               @PathVariable("id") Long id) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Champion> champion = championService.find(id, user.get());
        if (champion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UpdateChampionRequest.dtoToEntityUpdater(request, champion.get());
        championService.update(champion.get());
        return ResponseEntity.accepted().build();
    }
}
