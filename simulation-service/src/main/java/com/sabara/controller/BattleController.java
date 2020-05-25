package com.sabara.controller;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.BattleResults;
import com.sabara.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battle")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BattleController {

    private final BattleService battleService;

    @PostMapping
    public BattleResults battle(@RequestBody BattleDTO battle){
        return battleService.battle(battle);
    }
}
