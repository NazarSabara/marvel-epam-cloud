package com.sabara.controller;

import com.sabara.dto.HeroDTO;
import com.sabara.model.resource.BattleResults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/battle")
public class BattleController {

    @PostMapping("/pvp")
    public BattleResults playerVsPlayerBattle(@RequestBody HeroDTO firstHero, @RequestBody HeroDTO secondHero){
        return null;
    }

    @PostMapping("/tvt")
    public BattleResults teamVsTeamBattle(@RequestBody List<HeroDTO> firstTeam, @RequestBody List<HeroDTO> secondTeam){
        return null;
    }
}
