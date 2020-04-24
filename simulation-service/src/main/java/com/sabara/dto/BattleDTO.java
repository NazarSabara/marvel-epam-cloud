package com.sabara.dto;

import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleType;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BattleDTO {

    private List<HeroDTO> firstTeam;
    private List<HeroDTO> secondTeam;
    private BattleMap map;
    private BattleType battleType;
}
