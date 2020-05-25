package com.sabara.controller;

import com.sabara.dto.BattleResultsDto;
import com.sabara.model.entity.HeroStats;
import com.sabara.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticController {

    private final StatisticService service;

    @GetMapping("/5most-picked-heroes")
    public Flux<HeroStats> get5MostPickedHeroes() {
        return service.get5MostPickedHeroes();
    }

    @PostMapping
    public void saveBattleResults(Mono<BattleResultsDto> battleResults){
        service.saveBattleResults(battleResults);
    }
}
