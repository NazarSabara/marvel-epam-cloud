package com.sabara.dto;

import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BattleDTO {

    private List<HeroDTO> heroes;
    private BattleMap map;
    private BattleType battleType;
}
