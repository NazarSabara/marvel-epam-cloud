package com.sabara.dto;

import com.sabara.model.resource.BattleMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BattleDTO {

    private List<HeroDTO> firstTeam;
    private List<HeroDTO> secondTeam;
    private BattleMap map;
}
