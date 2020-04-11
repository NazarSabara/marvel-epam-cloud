package com.sabara.controller;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import com.sabara.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/battle")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BattleController {

    private final BattleService battleService;

    @PostMapping("/")
    public BattleResults battle(@RequestBody BattleDTO battle){
        return battleService.battle(battle.getFirstTeam(), battle.getSecondTeam(), battle.getMap());
    }
}
