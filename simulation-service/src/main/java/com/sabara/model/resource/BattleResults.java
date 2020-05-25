package com.sabara.model.resource;

import lombok.Data;

import java.util.List;

@Data
public class BattleResults {

    private BattleType battleType;
    private String winnerTag;
    private List<String> survivors;
    private List<String> winners;
    private List<String> losers;
    private long battleDuration;
    private BattleMap map;

}
