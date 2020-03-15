package com.sabara.model.resource;

import lombok.Data;

import java.util.List;

@Data
public class BattleResults {

    private String battleType;
    private String winner;
    private List<String> survivors;
    private long battleDuration;
    private String map;

}
