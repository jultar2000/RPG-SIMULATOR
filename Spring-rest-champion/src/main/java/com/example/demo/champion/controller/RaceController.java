package com.example.demo.champion.controller;

import com.example.demo.champion.dto.CreateRaceRequest;
import com.example.demo.champion.dto.GetRaceResponse;
import com.example.demo.champion.dto.GetRacesResponse;
import com.example.demo.champion.dto.UpdateRaceRequest;
import com.example.demo.champion.entity.Race;
import com.example.demo.champion.service.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/races")
public class RaceController {

    RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public ResponseEntity<GetRacesResponse> getRaces() {
        List<Race> all = raceService.findAll();
        GetRacesResponse getRacesResponse = GetRacesResponse.entityToDtoMapper(all);
        return ResponseEntity.ok(getRacesResponse);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetRaceResponse> getRace(@PathVariable("name") String name) {
        return raceService.find(name)
                .map(race -> ResponseEntity.ok(GetRaceResponse.entityToDtoMapper(race)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createRace(@RequestBody CreateRaceRequest request) {
        Race race = CreateRaceRequest.dtoToEntityMapper(request);
        raceService.add(race);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateRace(@RequestBody UpdateRaceRequest request,
                                           @PathVariable("name") String name) {
        Optional<Race> race = raceService.find(name);
        if(race.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UpdateRaceRequest.dtoToEntityMapper(request, race.get());
        raceService.update(race.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteRace(@PathVariable("name") String name){
        Optional<Race> race = raceService.find(name);
        if(race.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        raceService.delete(name);
        return ResponseEntity.accepted().build();
    }
}
