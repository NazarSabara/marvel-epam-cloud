package com.sabara.controller;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import com.sabara.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battle")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BattleController {

    private final BattleService battleService;

    @PostMapping("/start")
    public BattleResults battle(@RequestBody BattleDTO battle){
        return battleService.battle(battle);
    }
}
