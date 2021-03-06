package com.sabara.model.resource;

import lombok.Data;

import java.util.List;

@Data
public class BattleResults {

    private BattleType battleType;
    private String winner;
    private List<String> survivors;
    private long battleDuration;
    private BattleMap map;

}
